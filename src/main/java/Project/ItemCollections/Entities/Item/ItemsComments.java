package Project.ItemCollections.Entities.Item;

import javax.persistence.*;

@Entity
public class ItemsComments {
    @Id
    @Column(name="itemcomment_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    @Column(columnDefinition = "TEXT")
    private String comment;
}
