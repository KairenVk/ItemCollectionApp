package Project.ItemCollections.Entities.User;

import Project.ItemCollections.Entities.Item.Item;
import Project.ItemCollections.Entities.Collection.Collection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String username;
    private String email;
    private String password;

    private Boolean blocked = false;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_likes",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private Set<Item> itemLikes = new HashSet<>();

    @OneToMany(mappedBy="collectionOwner")
    private Set<Collection> ownedCollections;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role) {
            roles.add(role);
    }

    public Set<Item> getItemLikes() {
        return itemLikes;
    }

    public void setItemLikes(Set<Item> itemLikes) {
        this.itemLikes = itemLikes;
    }

    public void addItemLike(Item item) {
        itemLikes.add(item);
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Set<Collection> getOwnedCollections() {
        return ownedCollections;
    }

    public void setOwnedCollections(Set<Collection> ownedCollections) {
        this.ownedCollections = ownedCollections;
    }

    public void removeOwnedCollection(Collection collection) {
        ownedCollections.remove(collection);
    }
}
