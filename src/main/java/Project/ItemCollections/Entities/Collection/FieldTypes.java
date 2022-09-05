package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FieldTypes {

    @Id
    @Column(name="fieldtype_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="fieldTypes_generator")
    @SequenceGenerator(name="heroku_4884a911dcd0356", sequenceName = "fieldTypes_seq")
    private Integer id;

    private String nameType;

    @OneToMany (mappedBy="fieldType")
    private List<CollectionsFields> CollectionField = new ArrayList<>();

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

    public List<CollectionsFields> getCollectionField() {
        return CollectionField;
    }

    public void setCollectionField(List<CollectionsFields> collectionField) {
        CollectionField = collectionField;
    }
}
