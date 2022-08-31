package Project.ItemCollections.Entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Role implements Serializable {

    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="role_generator")
    @SequenceGenerator(name="heroku_d95adc693c777bb", sequenceName = "role_seq")
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void addUsers(User user) {
        users.add(user);
    }

}
