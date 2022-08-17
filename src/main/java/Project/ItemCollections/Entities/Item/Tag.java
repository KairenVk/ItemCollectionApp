package Project.ItemCollections.Entities.Item;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @Column(name="tag_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String tagName;
    private Integer tagWeight;
    @ManyToMany(mappedBy = "itemTags")
    private Set<Item> items = new HashSet<>();

    public Integer getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Integer tagWeight) {
        this.tagWeight = tagWeight;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
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
