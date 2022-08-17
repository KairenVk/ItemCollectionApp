package Project.ItemCollections.Entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements Serializable {

    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void addUsers(User user) {
        users.add(user);
    }

}
