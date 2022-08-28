package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.*;
import Project.ItemCollections.Entities.Item.ItemsComments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomFieldsService customFieldsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ItemsCommentsRepository itemsCommentsRepository;

    @Autowired
    private UserService userService;

    public void addItem(Item item, Integer collectionId, List<String> tags, List<String> customFieldsNames, List<String> customFieldsValues, MultipartFile image) {

        fileService.validateFile(image);
        User loggedInUser = userService.getLoggedUser();

        Item n = new Item();
        n.setItemCollection(collectionRepository.getById(collectionId));
        for (String tag : tags) {
            Tag m = tagRepository.findByTagName(tag);
            n.addItemTag(m);
        }
        n.setItemName(item.getItemName());
        n.setItemOwner(loggedInUser);
        itemRepository.save(n);
        String fileUrl = fileService.uploadFile(image, n.getId());
        n.setImageUrl(fileUrl);
        itemRepository.save(n);
        collectionRepository.getById(collectionId).addItemToCollection(n);
        if(customFieldsNames != null && customFieldsValues != null)
            customFieldsService.CreateItemCustomFields(n, customFieldsNames, customFieldsValues);
    }
    public void likeItem(Integer id) {
        User loggedInUser = userService.getLoggedUser();

        if(!loggedInUser.getItemLikes().contains(itemRepository.getById(id)))
            loggedInUser.addItemLike(itemRepository.getById(id));
    }

    public void dislikeItem(Integer id) {
        userService.getLoggedUser().removeItemLike(itemRepository.getById(id));
    }

    public void addComment(String comment, Integer itemId) {
        User loggedInUser = userService.getLoggedUser();

        ItemsComments n = new ItemsComments();
        n.setItem(itemRepository.getById(itemId));
        n.setComment(comment);
        n.setAuthor(loggedInUser);
        itemsCommentsRepository.save(n);
    }
}
