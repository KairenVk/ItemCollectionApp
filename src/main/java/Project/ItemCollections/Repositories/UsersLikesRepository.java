package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Entities.User.UsersLikes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersLikesRepository extends CrudRepository<UsersLikes, Integer> {

    public void deleteByLikedItem(Item item);

    public UsersLikes findByLikedItemAndUserWhoLiked(Item item, User user);

    public void deleteByLikedItemAndUserWhoLiked(Item item, User user);

    public List<UsersLikes> findUserWhoLikedByLikedItem(Item item);
}