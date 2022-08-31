package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ItemRepository extends CrudRepository<Item, Integer> {
    public List<Item> findByItemOwner(User user);

    public List<Item> findByItemCollection(Collection collection);

    public Item getById(Integer id);

    @Modifying
    @Query(
            value = "select * from item order by item_id desc limit 5",
            nativeQuery = true
    )
    public List<Item> findLatestItems();

    @Query(
            value = "select collection_id, count(collection_id) as ItemCount from item group by collection_id order by count(collection_id) desc limit 5",
            nativeQuery = true
    )
    public List<Object[]> findLargestCollections();
}
