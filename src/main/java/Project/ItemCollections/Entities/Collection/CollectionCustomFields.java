package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;

@Entity
public class CollectionCustomFields {

    @Id
    @Column(name="customfield_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="fieldtype_id", nullable=false)
    private FieldTypes fieldType;

    private String name;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable = false)
    private Collection collection;

}
