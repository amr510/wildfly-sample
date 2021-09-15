package explaineverything.wildfly.rest.service;

import explaineverything.wildfly.ejb.GroupEJB;
import explaineverything.wildfly.ejb.UserEJB;
import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.model.User;
import explaineverything.wildfly.rest.model.GroupResponseDto;
import explaineverything.wildfly.rest.model.UpdateGroupUserRequestDto;
import explaineverything.wildfly.rest.model.UserResponseDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/group-members")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupMembershipService {
    @Inject
    private GroupEJB groupService;

    @POST
    @Path("")
    public Response addUsersToGroup(UpdateGroupUserRequestDto updateGroupUserRequestDto) {
        groupService.addGroupMember(updateGroupUserRequestDto.getGroupId(), updateGroupUserRequestDto.getUserId());
        return Response.ok().build();
    }

    @DELETE
    @Path("")
    public Response removeUsersFromGroup(UpdateGroupUserRequestDto updateGroupUserRequestDto) {
        groupService.deleteGroupMember(updateGroupUserRequestDto.getGroupId(), updateGroupUserRequestDto.getUserId());
        return Response.ok().build();
    }

    @GET
    @Path("/{groupId}")
    public Response getGroupUsers(@PathParam("groupId") long groupId) {
        Group groupWithMembers = groupService.getGroupWithMembers(groupId);
        List<UserResponseDto> groupMembers = groupWithMembers.getUsers().stream()
                .map(UserResponseDto::fromUser)
                .collect(Collectors.toList());

        return Response.ok().entity(groupMembers).build();
    }

}
