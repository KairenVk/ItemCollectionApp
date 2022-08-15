package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;

@Entity
public class Collection {

    @Id
    @Column(name="collection_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable=false)
    private CollectionTopics collectionTopic;
}
