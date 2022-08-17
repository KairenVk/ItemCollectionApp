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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}