package explaineverything.wildfly.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import explaineverything.wildfly.model.Type;

import java.io.Serializable;

public class CreateUserRequestDto implements Serializable {
	private final String name;
    private final String email;
    private final Type type;

    @JsonCreator
    public CreateUserRequestDto(@JsonProperty("name") String name,
                                @JsonProperty("email") String email,
                                @JsonProperty("type") Type type) {
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Type getType() {
        return type;
    }
}
