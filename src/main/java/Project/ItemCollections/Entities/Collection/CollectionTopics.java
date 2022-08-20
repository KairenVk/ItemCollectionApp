package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CollectionTopics {

    @Id
    @Column(name="topic_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String topicName;

    @OneToMany(mappedBy="collectionTopic")
    private Set<Collection> topicCollections;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Set<Collection> getTopicCollections() {
        return topicCollections;
    }

    public void setTopicCollections(Set<Collection> topicCollections) {
        this.topicCollections = topicCollections;
    }

    public void removeTopicCollection(Collection collection) {
        topicCollections.remove(collection);
    }
}
