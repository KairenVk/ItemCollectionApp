package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Item implements Serializable {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="item_generator")
    @SequenceGenerator(name="item", sequenceName ="item_seq")
    private Integer id;

    @OneToMany(mappedBy="item")
    Set<ItemsInCollections> itemsInCollections;
}
