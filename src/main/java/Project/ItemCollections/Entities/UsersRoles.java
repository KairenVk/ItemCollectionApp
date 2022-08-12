package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UsersRoles implements Serializable {

    @Id
    @Column (name = "usersRoles_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="usersRoles_generator")
    @SequenceGenerator(name="usersRoles", sequenceName ="usersRoles_seq")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
