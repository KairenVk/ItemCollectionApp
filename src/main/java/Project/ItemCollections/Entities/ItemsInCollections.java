package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ItemsInCollections implements Serializable {

    @Id
    @Column(name="itemsincollections_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="itemsincollections_generator")
    @SequenceGenerator(name="itemsInCollections", sequenceName ="itemsincollections_seq")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="collection_id")
    private Collection collection;


    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
