package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class CollectionTypes implements Serializable {
    @Id
    @Column(name = "collectionType_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="colltectTypes_generator")
    @SequenceGenerator(name="collectionTypes", sequenceName ="collectTypes_seq")
    private Integer id;
    private String collectionType;

    @OneToMany
    private Set<Collection> collections;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }
}
