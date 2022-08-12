package Project.ItemCollections.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @Column (name = "user_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_generator")
    @SequenceGenerator(name="user", sequenceName ="user_seq")
    private Integer id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UsersRoles> userRoles;

    public User() {
        userRoles = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UsersRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UsersRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
