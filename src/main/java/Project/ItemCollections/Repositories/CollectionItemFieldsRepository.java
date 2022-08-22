package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CollectionItemFieldsRepository extends CrudRepository<CollectionItemFields, Integer> {
}
