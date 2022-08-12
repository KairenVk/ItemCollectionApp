package Project.ItemCollections.Services;

import Project.ItemCollections.Entities.Role;
import Project.ItemCollections.Entities.User;
import Project.ItemCollections.Entities.UsersRoles;
import Project.ItemCollections.Repositories.RoleRepository;
import Project.ItemCollections.Repositories.UserRepository;
import Project.ItemCollections.Repositories.UsersRolesRepository;
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
    private UsersRolesRepository usersRolesRepository;

    @Autowired
    private RoleRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        User n = new User();
        UsersRoles m = new UsersRoles();
        Role r = rolesRepository.findByRoleName("USER");
        n.setUsername(user.getUsername());
        n.setEmail(user.getEmail());
        n.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(n);
        m.setUser(n);
        m.setRole(r);
        usersRolesRepository.save(m);

    }


    public void updateUser(String username) {
    }


    public void deleteUser(String id) {

    }
}
