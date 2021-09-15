package explaineverything.wildfly.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_group")
@NamedEntityGraph(name = "groupWithMembersGraph", attributeNodes = @NamedAttributeNode("users"))
@SequenceGenerator(name = "APP_GROUP_ID_SEQ", initialValue = 1, allocationSize = 50, sequenceName = "APP_GROUP_ID_SEQ")
public class Group {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_GROUP_ID_SEQ")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column
    private LocalDateTime created;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_MEMBER", joinColumns = @JoinColumn(name = "GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void addUser(User userToAdd) {
        users.add(userToAdd);
        userToAdd.getGroups().remove(this);
    }

    public void removeUser(User userToRemove) {
        users.remove(userToRemove);
        userToRemove.getGroups().remove(this);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
