package Project.ItemCollections.Entities.Collection;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.User.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Collection implements Serializable {

    @Id
    @Column(name="collection_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="collection_generator")
    @SequenceGenerator(name="heroku_d95adc693c777bb", sequenceName = "collection_seq")
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
    private List<CollectionsFields> collectionFields = new ArrayList<>();

    @OneToMany(mappedBy="itemCollection")
    private List<Item> itemsInCollection = new ArrayList<>();

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

    public List<CollectionsFields> getCollectionFields() {
        return collectionFields;
    }

    public void setCollectionFields(List<CollectionsFields> collectionFields) {
        this.collectionFields = collectionFields;
    }

    public User getCollectionOwner() {
        return collectionOwner;
    }

    public void setCollectionOwner(User collectionOwner) {
        this.collectionOwner = collectionOwner;
    }

    public List<Item> getItemsInCollection() {
        return itemsInCollection;
    }

    public void setItemsInCollection(List<Item> itemsInCollection) {
        this.itemsInCollection = itemsInCollection;
    }

    public void addItemToCollection(Item item) {
        itemsInCollection.add(item);
    }

    public void addFieldToCollection(CollectionsFields customfield) {
        collectionFields.add(customfield);
    }


}
