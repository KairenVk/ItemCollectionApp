package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.User.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository  extends CrudRepository<Role, Integer> {
        public Role findByName(String name);
}