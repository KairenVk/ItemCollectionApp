package Project.ItemCollections.Entities.Item;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String itemName;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "item_tags",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> itemTags;
    private Integer likes;
    @OneToMany(mappedBy="item")
    private Set<ItemsComments> comments;

}
