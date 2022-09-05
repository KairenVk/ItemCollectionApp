package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionsFields;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionsFieldsRepository extends CrudRepository<CollectionsFields, Integer> {

    List<CollectionsFields> findByCollection(Collection collection);
    CollectionsFields findByNameAndCollectionId(String name, Integer collectionId);

    void deleteByCollection(Collection collection);
}
