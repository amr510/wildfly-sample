package explaineverything.wildfly.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import explaineverything.wildfly.model.Group;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GroupResponseDto implements Serializable {
    @JsonProperty("id")
    private final long id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("created")
    private final LocalDateTime created;

    public GroupResponseDto(long id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static GroupResponseDto fromGroup(Group group){
        return new GroupResponseDto(group.getId(), group.getName(), group.getCreated());
    }
}
