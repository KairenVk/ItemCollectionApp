package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.User.Role;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.RoleRepository;
import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }
        User n = new User();
        Role r = roleRepository.findByName("ROLE_USER");
        n.setUsername(user.getUsername());
        n.setEmail(user.getEmail());
        n.setPassword(passwordEncoder.encode(user.getPassword()));
        n.addRole(r);
        userRepository.save(n);
        return true;
    }


    public void updateUser(String username) {
    }


    public void deleteUsers(List<String> userUsernames) {
        for (String user: userUsernames) {
            User n = userRepository.findByUsername(user);
            n.setRoles(null);
            n.setItemLikes(null);
            n.setOwnedCollections(null);
            userRepository.delete(n);
        }
    }

    public void editUsers(List<String> userUsernames, String action) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        boolean currentUserInList = userUsernames.contains(currentUser);
        for (String user: userUsernames) {
            User n = userRepository.findByUsername(user);
            switch(action){
                case "block":
                    n.setBlocked(true);
                    break;
                case "unblock":
                    n.setBlocked(false);
                    break;
                case "delete":
                    deleteUsers(userUsernames);
                    break;
                case "grantAdmin":
                    if(!n.getRoles().contains(roleRepository.findByName("ROLE_ADMIN")))
                        n.addRole(roleRepository.findByName("ROLE_ADMIN"));
                    break;
                case "revokeAdmin":
                    n.removeRole(roleRepository.findByName("ROLE_ADMIN"));
                    break;
            }

        }
        if (currentUserInList && (action == "block" || action == "delete" || action == "revokeAdmin"))
            SecurityContextHolder.clearContext();
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
