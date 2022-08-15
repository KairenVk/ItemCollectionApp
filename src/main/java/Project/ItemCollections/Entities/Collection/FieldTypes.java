package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FieldTypes {

    @Id
    @Column(name="fieldtype_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToMany (mappedBy="fieldType")
    private Set<CollectionCustomFields> type;

}
