package Project.ItemCollections.Entities.Collection;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CollectionCustomFieldsData {

    @Id
    @Column(name="customfield_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="fieldtype_id", nullable=false)
    private FieldTypes fieldType;

    private String name;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable = false)
    private Collection collection;

    @OneToMany (mappedBy="customFieldsData")
    private Set<CollectionItemFields> customField;

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

    public Set<CollectionItemFields> getCustomField() {
        return customField;
    }

    public void addCustomField(CollectionItemFields customField) {
        this.customField.add(customField);
    }
    public void setCustomField(Set<CollectionItemFields> customField) {
        this.customField = customField;
    }
}
