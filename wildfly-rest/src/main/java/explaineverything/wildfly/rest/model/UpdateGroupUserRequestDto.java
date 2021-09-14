package explaineverything.wildfly.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UpdateGroupUserRequestDto implements Serializable {
    private final long groupId;
    private final long userId;

    @JsonCreator
    public UpdateGroupUserRequestDto(@JsonProperty("groupId") long groupId,
                                     @JsonProperty("userId") long userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }
}
