package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionRepository extends CrudRepository<Collection, Integer>
{
    public List<Collection> findByUserId(Integer userId);
    public Collection getById(Integer id);
}
