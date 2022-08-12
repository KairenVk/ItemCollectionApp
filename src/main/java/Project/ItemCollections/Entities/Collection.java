package Project.ItemCollections.Entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Collection implements Serializable {

    @Id
    @Column(name = "collection_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="collection_generator")
    @SequenceGenerator(name="collection", sequenceName ="collection_seq")
    private Integer id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name="collectionType_id")
    private CollectionTypes collectionType;

    @OneToMany(mappedBy="collection")
    Set<ItemsInCollections> itemsInCollections;
}
