package Project.ItemCollections.Entities.Collection;

import Project.ItemCollections.Entities.User.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Collection {

    @Id
    @Column(name="collection_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable=false)
    private CollectionTopics collectionTopic;

    @OneToMany(mappedBy="collection")
    private Set<CollectionCustomFieldsData> customFields;

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

    public Set<CollectionCustomFieldsData> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Set<CollectionCustomFieldsData> customFields) {
        this.customFields = customFields;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString()
    {
        return "Collection{" + "id=" + id + ", user=" + user + ", name='" + name + '\'' + ", description='" + description + '\'' + ", collectionTopic=" + collectionTopic + '}';
    }
}
