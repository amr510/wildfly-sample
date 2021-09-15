package explaineverything.wildfly.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@NamedEntityGraph(name = "userWithGroupsGraph", attributeNodes = @NamedAttributeNode("groups"))
@SequenceGenerator(name = "APPUSER_ID_SEQ", initialValue = 1, allocationSize = 50, sequenceName = "APP_USER_ID_SEQ")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_ID_SEQ")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TYPE")
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @ManyToMany(mappedBy = "users")
	private Set<Group> groups = new HashSet<>();
    @Column(name = "CREATED")
    private LocalDateTime created;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
