package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.Item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface CollectionItemFieldsRepository extends CrudRepository<CollectionItemFields, Integer> {

    public Set<CollectionItemFields> deleteByItemId(Item item);
}
