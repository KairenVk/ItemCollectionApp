package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemTags;
import org.springframework.data.repository.CrudRepository;

public interface ItemTagsRepository extends CrudRepository<ItemTags, Integer> {

    public void deleteByTaggedItem(Item item);
}
