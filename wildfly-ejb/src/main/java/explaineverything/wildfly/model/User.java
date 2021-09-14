package explaineverything.wildfly.model;

import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@SequenceGenerator(name = "APPUSER_ID_SEQ", initialValue = 1, allocationSize = 50, sequenceName = "APP_USER_ID_SEQ")
//@NamedNativeQueries({
//		@NamedNativeQuery( name = "getGroupMembers",
//		query = "SELECT u from app_user u JOIN group_member m on u.ID = m.USER_ID WHERE m.GROUP_ID = :group)")
//})
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
