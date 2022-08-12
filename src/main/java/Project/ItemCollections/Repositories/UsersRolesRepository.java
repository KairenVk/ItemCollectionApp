package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.UsersRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRolesRepository extends CrudRepository<UsersRoles, Integer> {
}
