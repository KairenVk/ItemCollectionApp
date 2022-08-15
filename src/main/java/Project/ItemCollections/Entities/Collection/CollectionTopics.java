package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CollectionTopics {

    @Id
    @Column(name="topic_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @OneToMany(mappedBy="collectionTopic")
    private Set<Collection> collections;
}
