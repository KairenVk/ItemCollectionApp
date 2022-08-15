package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.User.Role;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.RoleRepository;
import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        User n = new User();
        Role r = roleRepository.findByName("USER");
        n.setUsername(user.getUsername());
        n.setEmail(user.getEmail());
        n.setPassword(passwordEncoder.encode(user.getPassword()));
        n.addRole(r);
        userRepository.save(n);

    }


    public void updateUser(String username) {
    }


    public void deleteUser(String id) {

    }
}
