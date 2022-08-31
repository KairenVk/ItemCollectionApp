package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemsComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsCommentsRepository extends CrudRepository<ItemsComments, Integer> {

    public List<ItemsComments> findByItem(Item item);

    public List<ItemsComments> findAllByItemAndIdGreaterThan(Item item, Integer id);

    public void deleteByItem(Item item);
}
