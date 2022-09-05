package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.ItemRepository;
import Project.ItemCollections.Repositories.RoleRepository;
import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    @Scope(value = "prototype")
    public boolean hasCollectionPermission(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            Collection collection = collectionRepository.getById(id);
            if (collection.getCollectionOwner() == user) {
                return true;
            }
            else return user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"));
        }
        return false;
    }
    @Bean
    @Scope(value = "prototype")
    public boolean hasItemPermission(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
            Item item = itemRepository.getById(id);
            if (item.getItemOwner() == user)
                return true;
            return user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"));
        }
        return false;
    }

    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User loggedInUser = userRepository.findByUsername(((UserDetails) principal).getUsername());
            return loggedInUser;
        }
        return null;
    }
}
