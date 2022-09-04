package Project.ItemCollections.Repositories;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Entities.User.UsersLikes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersLikesRepository extends CrudRepository<UsersLikes, Integer> {

    void deleteByLikedItem(Item item);

    UsersLikes findByLikedItemAndUserWhoLiked(Item item, User user);

    void deleteByLikedItemAndUserWhoLiked(Item item, User user);

    List<UsersLikes> findUserWhoLikedByLikedItem(Item item);

    void deleteByUserWhoLiked(User user);
}