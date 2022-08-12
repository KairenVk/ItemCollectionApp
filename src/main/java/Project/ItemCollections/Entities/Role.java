package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable {
    @Id
    @Column (name = "role_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="role_generator")
    @SequenceGenerator(name="role", sequenceName ="role_seq")
    private Integer id;
    private String roleName;

    @OneToMany(mappedBy="role")
    Set<UsersRoles> userRoles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<UsersRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UsersRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
