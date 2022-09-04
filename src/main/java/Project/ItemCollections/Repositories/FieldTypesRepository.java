package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.FieldTypes;
import org.springframework.data.repository.CrudRepository;

public interface FieldTypesRepository extends CrudRepository<FieldTypes, Integer> {

    FieldTypes getByNameType(String nameType);
}
