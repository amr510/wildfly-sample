package explaineverything.wildfly.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import explaineverything.wildfly.model.User;

import java.time.LocalDateTime;

public class UserResponseDto {
    @JsonProperty("id")
    private final long id;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("created")
    private final LocalDateTime created;

    public UserResponseDto(long id, String email, String name, LocalDateTime created) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public static UserResponseDto fromUser(User user){
        return new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getCreated());
    }
}
