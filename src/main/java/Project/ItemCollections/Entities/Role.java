package Project.ItemCollections.Entities;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column (name = "role_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="role_generator")
    @SequenceGenerator(name="itemcollections", sequenceName ="role_seq")
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
