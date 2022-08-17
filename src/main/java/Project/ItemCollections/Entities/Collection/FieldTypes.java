package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FieldTypes {

    @Id
    @Column(name="fieldtype_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nameType;

    @OneToMany (mappedBy="fieldType")
    private Set<CollectionCustomFieldsData> CollectionField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Set<CollectionCustomFieldsData> getCollectionField() {
        return CollectionField;
    }

    public void setCollectionField(Set<CollectionCustomFieldsData> collectionField) {
        CollectionField = collectionField;
    }
}
