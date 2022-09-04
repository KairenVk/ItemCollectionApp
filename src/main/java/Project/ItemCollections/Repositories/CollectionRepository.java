package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionRepository extends CrudRepository<Collection, Integer>
{
    List<Collection> findByCollectionOwner(User user);
    Collection getById(Integer id);
}
