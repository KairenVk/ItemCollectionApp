package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemTags;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Entities.User.UsersLikes;
import Project.ItemCollections.Repositories.*;
import Project.ItemCollections.Entities.Item.ItemsComments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomFieldsService customFieldsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ItemsCommentsRepository itemsCommentsRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ItemTagsRepository itemTagsRepository;

    @Autowired
    private CollectionsFieldsDataRepository collectionsFieldsDataRepository;

    @Autowired
    private UsersLikesRepository usersLikesRepository;

    public void editItem(Item item, Integer itemId, Integer collectionId, List<String> tags, List<String> customFieldsNames, List<String> customFieldsValues, MultipartFile image) {

        if (!image.isEmpty()) {
            fileService.validateFile(image);
        }

        Item n = itemRepository.getById(itemId);

        n.setItemName(item.getItemName());
        String imageUrl = fileService.uploadFile(image, n.getId());
        n.setImageUrl(imageUrl);
        if(tags != null) {
            itemTagsRepository.deleteByTaggedItem(n);
            for (String tag: tags) {
                Tag newTag = tagRepository.findByTagName(tag);
                ItemTags itemTag = new ItemTags();
                itemTag.setTaggedItem(n);
                itemTag.setItemTag(newTag);
                itemTagsRepository.save(itemTag);
            }
        }
        itemRepository.save(n);
        customFieldsService.updateItemCustomFields(n, customFieldsNames, customFieldsValues);


    }
    public void createItem(Item item, Integer collectionId, List<String> tags, List<String> customFieldsNames, List<String> customFieldsValues, MultipartFile image) {
        if (!image.isEmpty()) {
            fileService.validateFile(image);
        }

        Item n = new Item();
        Collection itemCollection = collectionRepository.getById(collectionId);
        User loggedInUser = authService.getLoggedUser();
        n.setItemOwner(loggedInUser);
        n.setItemCollection(itemCollection);
        n.setItemName(item.getItemName());
        itemRepository.save(n);
        if (tags != null) {
            for (String tag : tags) {
                Tag m = tagRepository.findByTagName(tag);
                ItemTags newTag = new ItemTags();
                newTag.setItemTag(m);
                newTag.setTaggedItem(n);
                itemTagsRepository.save(newTag);
            }
        }
        n.setItemName(item.getItemName());
        String fileUrl = fileService.uploadFile(image, n.getId());
        n.setImageUrl(fileUrl);
        itemRepository.save(n);
        itemCollection.addItemToCollection(n);
        if(customFieldsNames != null && customFieldsValues != null)
            customFieldsService.createItemCustomFields(n, customFieldsNames, customFieldsValues);
    }

    public void likeItem(Integer itemId) {
        User loggedInUser = authService.getLoggedUser();
        Item item = itemRepository.getById(itemId);
        if(usersLikesRepository.findByLikedItemAndUserWhoLiked(item,loggedInUser) == null) {
            UsersLikes like = new UsersLikes();
            like.setLikedItem(item);
            like.setUserWhoLiked(loggedInUser);
            usersLikesRepository.save(like);
        }

    }

    public void dislikeItem(Integer itemId) {
        User loggedInUser = authService.getLoggedUser();
        Item item = itemRepository.getById(itemId);
        usersLikesRepository.deleteByLikedItemAndUserWhoLiked(item, loggedInUser);
    }

    public void addComment(String comment, Integer itemId) {
        User loggedInUser = authService.getLoggedUser();
        ItemsComments n = new ItemsComments();
        n.setItem(itemRepository.getById(itemId));
        n.setComment(comment);
        n.setAuthor(loggedInUser);
        itemsCommentsRepository.save(n);
    }

    public void deleteItem(Integer itemId) {
        Item item = itemRepository.getById(itemId);
        fileService.deleteFile(item.getImageUrl());
        itemTagsRepository.deleteByTaggedItem(item);
        collectionsFieldsDataRepository.deleteByItemId(item);
        usersLikesRepository.deleteByLikedItem(item);
        itemsCommentsRepository.deleteByItem(item);
        itemRepository.delete(item);

    }
}
