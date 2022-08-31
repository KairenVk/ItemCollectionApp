package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.CollectionCustomFieldsData;
import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.Item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionItemFieldsRepository extends CrudRepository<CollectionItemFields, Integer> {

    public List<CollectionItemFields> deleteByItemId(Item item);

    public List<CollectionItemFields> getByItemId(Item item);

    public CollectionItemFields getByItemIdAndCustomFieldsData(Item item, CollectionCustomFieldsData customFieldsData);
}
