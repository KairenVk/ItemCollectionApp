package Project.ItemCollections.Entities.Item;

import Project.ItemCollections.Entities.User.User;

import javax.persistence.*;

@Entity
public class ItemsComments {
    @Id
    @Column(name="itemcomment_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="itemComments_generator")
    @SequenceGenerator(name="heroku_d95adc693c777bb", sequenceName = "itemComments_seq")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User author;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}