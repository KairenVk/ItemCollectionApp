package Project.ItemCollections.Entities.Item;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Collection.CollectionItemFields;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Entities.User.UsersLikes;
import Project.ItemCollections.Repositories.TagRepository;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item implements Serializable
{
    @Autowired
    @Transient
    private TagRepository tagRepository;
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
    private List<CollectionItemFields> customItemFields = new ArrayList<>();

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

    public List<CollectionItemFields> getCustomItemFields() {
        return customItemFields;
    }

    public void addCustomItemField(CollectionItemFields customField) {
        customItemFields.add(customField);
    }

    public void setCustomItemFields(List<CollectionItemFields> customItemFields) {
        this.customItemFields = customItemFields;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Override
    public String toString()
    {
        return "Item{" + "id=" + id + ", itemName='" + itemName + '\'' + ", itemTags=" + itemTags + ", usersWhoLiked=" + usersWhoLiked + ", comments=" + comments + ", itemCollection=" + itemCollection + ", itemOwner=" + itemOwner + ", customItemFields=" + customItemFields + ", imageUrl='" + imageUrl + '\'' + '}';
    }
}
