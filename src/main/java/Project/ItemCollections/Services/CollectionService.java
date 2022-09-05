package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionTopics;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.CollectionTopicsRepository;
import Project.ItemCollections.Repositories.CollectionsFieldsRepository;
import Project.ItemCollections.Repositories.FieldTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionTopicsRepository collectionTopicsRepository;

    @Autowired
    private CollectionsFieldsRepository collectionsFieldsRepository;

    @Autowired
    private FieldTypesRepository fieldTypesRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ItemService itemService;
    @Autowired
    private HtmlServiceImpl htmlService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomFieldsService customFieldsService;

    public void createCollection(Collection collection, String topicName, List<String> fieldNames, List<String> customFields, MultipartFile image) {

        if (!image.isEmpty()) {
            fileService.validateFile(image);
        }

        User loggedInUser = authService.getLoggedUser();

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);
        Collection n = new Collection();

        n.setCollectionOwner(loggedInUser);
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        n.setDescription(HtmlUtils.htmlEscape(collection.getDescription()));
        collectionRepository.save(n);
        String imageUrl = fileService.uploadFile(image, n.getId());
        n.setImageUrl(imageUrl);
        if (fieldNames != null) {
            customFieldsService.createCollectionCustomFields(customFields, fieldNames, n);
        }
        collectionRepository.save(n);
    }

    public void editCollection(Collection collection, Integer id, String topicName) {

        CollectionTopics topic = collectionTopicsRepository.findByTopicName(topicName);
        Collection n = collectionRepository.getById(id);
        n.setDescription(HtmlUtils.htmlEscape(collection.getDescription()));
        n.setName(collection.getName());
        n.setCollectionTopic(topic);
        collectionRepository.save(n);

    }

    public void deleteCollection(Integer id) {
        Collection n = collectionRepository.getById(id);
        User owner = n.getCollectionOwner();
        owner.removeOwnedCollection(n);
        n.getCollectionTopic().removeTopicCollection(n);
        for (Item item : n.getItemsInCollection()) {
            itemService.deleteItem(item.getId());
        }
        collectionsFieldsRepository.deleteByCollection(n);
        collectionRepository.delete(n);
    }
}