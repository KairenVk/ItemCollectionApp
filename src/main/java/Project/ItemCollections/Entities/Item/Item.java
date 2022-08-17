package Project.ItemCollections.Entities.Item;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String itemName;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "item_tags",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> itemTags;
    private Integer likes;

    @OneToMany(mappedBy="item")
    private Set<ItemsComments> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Tag> getItemTags() {
        return itemTags;
    }

    public void setItemTags(Set<Tag> itemTags) {
        this.itemTags = itemTags;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Set<ItemsComments> getComments() {
        return comments;
    }

    public void setComments(Set<ItemsComments> comments) {
        this.comments = comments;
    }
}
