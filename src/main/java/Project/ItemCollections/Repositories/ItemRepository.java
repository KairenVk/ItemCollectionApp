package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemRepository extends CrudRepository<Item, Integer> {
    public List<Item> findByItemOwner(User user);

    public List<Item> findByItemCollection(Collection collection);

    public Item getById(Integer id);
}
