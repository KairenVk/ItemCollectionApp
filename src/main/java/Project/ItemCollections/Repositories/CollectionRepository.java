package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionRepository extends CrudRepository<Collection, Integer>
{
    public List<Collection> findByCollectionOwner(User user);
    public Collection getById(Integer id);
}
