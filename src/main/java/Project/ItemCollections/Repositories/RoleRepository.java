package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository  extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}