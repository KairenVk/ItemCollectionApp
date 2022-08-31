package Project.ItemCollections.Entities.User;

import Project.ItemCollections.Entities.Item.Item;

import javax.persistence.*;

@Entity
public class UsersLikes {
    @Id
    @Column(name="userslikes_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User userWhoLiked;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item likedItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserWhoLiked() {
        return userWhoLiked;
    }

    public void setUserWhoLiked(User userWhoLiked) {
        this.userWhoLiked = userWhoLiked;
    }

    public Item getLikedItem() {
        return likedItem;
    }

    public void setLikedItem(Item likedItem) {
        this.likedItem = likedItem;
    }
}
