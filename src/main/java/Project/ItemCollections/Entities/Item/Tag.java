package Project.ItemCollections.Entities.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @Column(name="tag_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="tag_generator")
    @SequenceGenerator(name="heroku_4884a911dcd0356", sequenceName = "tag_seq")
    private Integer id;

    private String tagName;

    @OneToMany(mappedBy = "itemTag")
    private List<ItemTags> items = new ArrayList<>();

    public List<ItemTags> getItems() {
        return items;
    }

    public void setItems(List<ItemTags> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
