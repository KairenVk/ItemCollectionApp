package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public void addItem(Item item, Integer collectionId, List<String> tags, List<String> customFieldsNames, List<String> customFieldsValues) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        Item n = new Item();
        n.setItemCollection(collectionRepository.getById(collectionId));
        for (String tag : tags) {
            Tag m = tagRepository.findByTagName(tag);
            n.addItemTag(m);
        }
        n.setItemName(item.getItemName());
        n.setItemOwner(loggedInUser);
        itemRepository.save(n);
        collectionRepository.getById(collectionId).addItemToCollection(n);
        if(customFieldsNames != null && customFieldsValues != null)
            customFieldsService.CreateItemCustomFields(n, customFieldsNames, customFieldsValues);
    }
    public void likeItem(Integer id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        if(!loggedInUser.getItemLikes().contains(itemRepository.getById(id)))
            loggedInUser.addItemLike(itemRepository.getById(id));
    }

    public void dislikeItem(Integer id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());

        loggedInUser.removeItemLike(itemRepository.getById(id));
    }
}
