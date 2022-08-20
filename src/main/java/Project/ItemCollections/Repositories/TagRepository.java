package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TagRepository extends CrudRepository<Tag, Integer> {

    public Tag findByTagName(String tagName);
}
