package Project.ItemCollections.Entities.Item;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionsFieldsData;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Entities.User.UsersLikes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item implements Serializable {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String itemName;

    @OneToMany(mappedBy="taggedItem")
    private List<ItemTags> itemTags = new ArrayList<>();

    @OneToMany(mappedBy="userWhoLiked")
    private List<UsersLikes> usersWhoLiked;

    @OneToMany(mappedBy="item")
    private List<ItemsComments> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="collection_id", nullable=false)
    private Collection itemCollection;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User itemOwner;

    @OneToMany(mappedBy="itemId")
    private List<CollectionsFieldsData> collectionsFieldsData = new ArrayList<>();

    private String imageUrl;
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

    public List<ItemTags> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<ItemTags> itemTags) {
        this.itemTags = itemTags;
    }

    public void addItemTag(ItemTags itemTag) {
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

    public List<CollectionsFieldsData> getCollectionsFieldsData() {
        return collectionsFieldsData;
    }

    public void addCustomItemField(CollectionsFieldsData customField) {
        collectionsFieldsData.add(customField);
    }

    public void setCollectionsFieldsData(List<CollectionsFieldsData> collectionsFieldsData) {
        this.collectionsFieldsData = collectionsFieldsData;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
