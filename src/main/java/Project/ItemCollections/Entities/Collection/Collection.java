package Project.ItemCollections.Entities.Collection;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Collection implements Serializable {

    @Id
    @Column(name="collection_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User collectionOwner;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable=false)
    private CollectionTopics collectionTopic;

    @OneToMany(mappedBy="collection")
    private Set<CollectionCustomFieldsData> customFieldsData = new HashSet<>();

    @OneToMany(mappedBy="itemCollection")
    private Set<Item> itemsInCollection = new HashSet<>();

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CollectionTopics getCollectionTopic() {
        return collectionTopic;
    }

    public void setCollectionTopic(CollectionTopics collectionTopic) {
        this.collectionTopic = collectionTopic;
    }

    public Set<CollectionCustomFieldsData> getCustomFieldsData() {
        return customFieldsData;
    }

    public void setCustomFieldsData(Set<CollectionCustomFieldsData> customFieldsData) {
        this.customFieldsData = customFieldsData;
    }

    public User getCollectionOwner() {
        return collectionOwner;
    }

    public void setCollectionOwner(User collectionOwner) {
        this.collectionOwner = collectionOwner;
    }

    public Set<Item> getItemsInCollection() {
        return itemsInCollection;
    }

    public void setItemsInCollection(Set<Item> itemsInCollection) {
        this.itemsInCollection = itemsInCollection;
    }

    public void addItemToCollection(Item item) {
        itemsInCollection.add(item);
    }

    public void addFieldToCollection(CollectionCustomFieldsData customfield) {
        customFieldsData.add(customfield);
    }
    @Override
    public String toString()
    {
        return "Collection{" + "id=" + id + ", user=" + collectionOwner + ", name='" + name + '\'' + ", description='" + description + '\'' + ", collectionTopic=" + collectionTopic + '}';
    }


}
