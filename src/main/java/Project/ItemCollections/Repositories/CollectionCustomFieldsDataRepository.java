package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionCustomFieldsData;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface CollectionCustomFieldsDataRepository extends CrudRepository<CollectionCustomFieldsData, Integer> {

    public List<CollectionCustomFieldsData> findByCollection(Collection collection);
    public CollectionCustomFieldsData findByNameAndCollectionId(String name, Integer collectionId);
}
