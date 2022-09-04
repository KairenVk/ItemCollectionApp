package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.CollectionsFieldsData;
import Project.ItemCollections.Entities.Item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionsFieldsDataRepository extends CrudRepository<CollectionsFieldsData, Integer> {

    List<CollectionsFieldsData> deleteByItemId(Item item);
}
