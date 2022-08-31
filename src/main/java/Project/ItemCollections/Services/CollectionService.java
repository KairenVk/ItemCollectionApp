package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionCustomFieldsData;
import Project.ItemCollections.Entities.Collection.CollectionTopics;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Exceptions.StorageException;
import Project.ItemCollections.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionTopicsRepository collectionTopicsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CollectionCustomFieldsDataRepository collectionCustomFieldsDataRepository;

    @Autowired
    private FieldTypesRepository fieldTypesRepository;

    @Autowired
    private CollectionItemFieldsRepository collectionItemFieldsRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private HtmlServiceImpl htmlService;

    @Autowired
    private ItemTagsRepository itemTagsRepository;

    public void createCollection(Collection collection, String topicName, List<String> fieldNames, List<String> customFields, MultipartFile image) {

        if (image != null) {
            fileService.validateFile(image);
        }

        User loggedInUser = userService.getLoggedUser();

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);
        Collection n = new Collection();

        n.setCollectionOwner(loggedInUser);
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        n.setDescription(htmlService.markdownToHtml(collection.getDescription()));
        collectionRepository.save(n);
        String imageUrl = fileService.uploadFile(image, n.getId());
        n.setImageUrl(imageUrl);
        if (fieldNames != null) {
            for (int i = 0; i < fieldNames.size(); i++) {
                CollectionCustomFieldsData field = new CollectionCustomFieldsData();
                field.setFieldType(fieldTypesRepository.getByNameType(customFields.get(i)));
                field.setName(fieldNames.get(i));
                field.setCollection(n);
                n.addFieldToCollection(field);
                collectionCustomFieldsDataRepository.save(field);
            }
        }
        collectionRepository.save(n);
    }

    public void editCollection(Collection collection, Integer id, String topicName) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);
        Collection n = collectionRepository.getById(id);
        n.setDescription(collection.getDescription());
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        collectionRepository.save(n);
    }

    public void deleteCollection(Integer id) {
        Collection n = collectionRepository.getById(id);
        User owner = n.getCollectionOwner();
        CollectionTopics topic = collectionTopicsRepository.findByTopicName(n.getCollectionTopic().getTopicName());

        topic.removeTopicCollection(n);
        Set<Item> items = n.getItemsInCollection();
        for (Item item : items) {
            itemTagsRepository.deleteByTaggedItem(item);
            collectionItemFieldsRepository.deleteByItemId(item);
            itemRepository.delete(item);
        }
        owner.removeOwnedCollection(n);

        collectionRepository.delete(n);
    }
}