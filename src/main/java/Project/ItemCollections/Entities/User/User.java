package Project.ItemCollections.Entities.User;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.Item.ItemsComments;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_generator")
    @SequenceGenerator(name="heroku_4884a911dcd0356", sequenceName = "user_seq")
    private Integer id;
    private String username;
    private String email;
    private String password;

    private Boolean blocked = false;

    @ManyToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy="userWhoLiked")
    private List<UsersLikes> itemLikes = new ArrayList<>();

    @OneToMany(mappedBy="collectionOwner")
    private List<Collection> ownedCollections;

    @OneToMany(mappedBy="author")
    private List<ItemsComments> commentsList;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role) {
            roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public List<Collection> getOwnedCollections() {
        return ownedCollections;
    }

    public void setOwnedCollections(List<Collection> ownedCollections) {
        this.ownedCollections = ownedCollections;
    }

    public void removeOwnedCollection(Collection collection) {
        ownedCollections.remove(collection);
    }

    public List<ItemsComments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<ItemsComments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<UsersLikes> getItemLikes() {
        return itemLikes;
    }

    public void setItemLikes(List<UsersLikes> itemLikes) {
        this.itemLikes = itemLikes;
    }
}
