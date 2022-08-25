package Project.ItemCollections.Entities.Item;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.User.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    private List<Tag> itemTags = new ArrayList<>();

    @ManyToMany(mappedBy="itemLikes")
    private List<User> usersWhoLiked;

    @OneToMany(mappedBy="item")
    private List<ItemsComments> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="collection_id", nullable=false)
    private Collection itemCollection;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User itemOwner;

    @OneToMany(mappedBy="itemId")
    private List<CollectionItemFields> customItemFields = new ArrayList<>();
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

    public List<Tag> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<Tag> itemTags) {
        this.itemTags = itemTags;
    }

    public void addItemTag(Tag itemTag) {
        itemTags.add(itemTag);
    }

    public List<ItemsComments> getComments() {
        return comments;
    }

    public void setComments(List<ItemsComments> comments) {
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

    public List getCustomItemFields() {
        return customItemFields;
    }

    public void addCustomItemField(CollectionItemFields customField) {
        customItemFields.add(customField);
    }

    public void removeItemTags() {
        itemTags.clear();
    }

    public List<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(List<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public void addUserWhoLiked(User user) {
        usersWhoLiked.add(user);
    }

    public void setCustomItemFields(List<CollectionItemFields> customItemFields) {
        this.customItemFields = customItemFields;
    }
}
