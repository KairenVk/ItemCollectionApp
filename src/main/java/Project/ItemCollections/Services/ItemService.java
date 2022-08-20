package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.Tag;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Repositories.TagRepository;
import Project.ItemCollections.Repositories.UserRepository;
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
    public void addItem(Item item, Integer collectionId, List<String> tags) {

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
        n.setLikes(0);
        itemRepository.save(n);
        collectionRepository.getById(collectionId).addItemToCollection(n);
    }
}
