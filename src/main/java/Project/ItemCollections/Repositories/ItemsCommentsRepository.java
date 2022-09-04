package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Item.ItemsComments;
import Project.ItemCollections.Entities.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsCommentsRepository extends CrudRepository<ItemsComments, Integer> {

    List<ItemsComments> findByItem(Item item);

    List<ItemsComments> findAllByItemAndIdGreaterThan(Item item, Integer id);

    void deleteByItem(Item item);

    void deleteByAuthor(User user);
}
