package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class CollectionCustomFieldsData {

    @Id
    @Column(name="customfield_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="collectionCustomFieldsData_generator")
    @SequenceGenerator(name="heroku_d95adc693c777bb", sequenceName = "collectionCustomFieldsData_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="fieldtype_id", nullable=false)
    private FieldTypes fieldType;

    private String name;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable = false)
    private Collection collection;

    @OneToMany (mappedBy="customFieldsData")
    private List<CollectionItemFields> customField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FieldTypes getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldTypes fieldType) {
        this.fieldType = fieldType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public List<CollectionItemFields> getCustomField() {
        return customField;
    }

    public void addCustomField(CollectionItemFields customField) {
        this.customField.add(customField);
    }
    public void setCustomField(List<CollectionItemFields> customField) {
        this.customField = customField;
    }
}
