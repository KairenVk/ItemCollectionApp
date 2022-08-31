package Project.ItemCollections.Entities.Item;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ItemTags implements Serializable {

    @Id
    @Column(name="itemtag_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item taggedItem;

    @ManyToOne
    @JoinColumn(name="tag_id", nullable = false)
    private Tag itemTag;

    public Item getTaggedItem() {
        return taggedItem;
    }

    public void setTaggedItem(Item taggedItem) {
        this.taggedItem = taggedItem;
    }

    public Tag getItemTag() {
        return itemTag;
    }

    public void setItemTag(Tag itemTag) {
        this.itemTag = itemTag;
    }
}
