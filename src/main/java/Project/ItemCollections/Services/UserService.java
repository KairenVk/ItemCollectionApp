package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Role;
import Project.ItemCollections.Entities.User;
import Project.ItemCollections.Repositories.RoleRepository;
import Project.ItemCollections.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
        Set<Role> defaultRole = new HashSet<>();
        defaultRole.add(roleRepository.findByName("USER"));
        n.setUsername(user.getUsername());
        n.setPassword(passwordEncoder.encode(user.getPassword()));
        n.setEmail(user.getEmail());
        n.setRoles(defaultRole);
        userRepository.save(n);
    }


    public void updateUser(String username) {
    }


    public void deleteUser(String id) {

    }
}
