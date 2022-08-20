package Project.ItemCollections.Entities.Item;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String itemName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_tags",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> itemTags = new HashSet<>();
    private Integer likes;

    @OneToMany(mappedBy="item")
    private Set<ItemsComments> comments;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable=false)
    private Collection itemCollection;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User itemOwner;
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

    public void addItemTag(Tag itemTag) {
        itemTags.add(itemTag);
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

    public Collection getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection itemCollection) {
        this.itemCollection = itemCollection;
    }

    public User getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(User itemOwner) {
        this.itemOwner = itemOwner;
    }
}
