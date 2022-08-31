package Project.ItemCollections.Entities.Item;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @Column(name="tag_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String tagName;
    private Integer tagWeight;
    @OneToMany(mappedBy = "itemTag")
    private List<ItemTags> items = new ArrayList<>();

    public Integer getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Integer tagWeight) {
        this.tagWeight = tagWeight;
    }

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
