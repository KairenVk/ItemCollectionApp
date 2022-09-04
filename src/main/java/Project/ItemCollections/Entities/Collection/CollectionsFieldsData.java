package Project.ItemCollections.Entities.Collection;

import Project.ItemCollections.Entities.Item.Item;

import javax.persistence.*;

@Entity
public class CollectionsFieldsData {

    @Id
    @Column(name="collectionfielddata_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item itemId;

    @ManyToOne
    @JoinColumn(name="collectionfield_id", nullable = false)
    private CollectionsFields collectionField;

    @Column(columnDefinition = "TEXT")
    private String fieldContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public CollectionsFields getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(CollectionsFields collectionField) {
        this.collectionField = collectionField;
    }

    public String getFieldContent() {
        return fieldContent;
    }

    public void setFieldContent(String fieldContent) {
        this.fieldContent = fieldContent;
    }
}
