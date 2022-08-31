package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TagRepository extends CrudRepository<Tag, Integer> {

    public Tag findByTagName(String tagName);
    @Modifying
    @Query(
            value = "select * from tag where tag_name like %:term%",
            nativeQuery = true
    )
    public List<Tag> findAllByTagName(String term);
}
