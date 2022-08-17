package Project.ItemCollections.Entities.Collection;

import Project.ItemCollections.Entities.Item.Item;

import javax.persistence.*;

@Entity
public class CollectionItemFields {

    @Id
    @Column(name="collectionItemField_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item itemId;

    @ManyToOne
    @JoinColumn(name="customfield_id", nullable = false)
    private CollectionCustomFieldsData customFieldsData;

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

    public CollectionCustomFieldsData getCustomFieldsData() {
        return customFieldsData;
    }

    public void setCustomFieldsData(CollectionCustomFieldsData customFieldsData) {
        this.customFieldsData = customFieldsData;
    }

    public String getFieldContent() {
        return fieldContent;
    }

    public void setFieldContent(String fieldContent) {
        this.fieldContent = fieldContent;
    }
}
