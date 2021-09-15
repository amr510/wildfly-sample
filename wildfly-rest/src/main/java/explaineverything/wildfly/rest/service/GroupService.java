package explaineverything.wildfly.rest.service;

import explaineverything.wildfly.ejb.GroupEJB;
import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.rest.model.CreateGroupRequestDto;
import explaineverything.wildfly.rest.model.GroupResponseDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupService {

    @Inject
    private GroupEJB service;

    @GET
    @Path("")
    public Response getAllGroups() {
        List<Group> groups = service.getAll();
        List<GroupResponseDto> groupResponseDtos = groups.stream().map(GroupResponseDto::fromGroup).collect(Collectors.toList());
        return Response.ok().entity(groupResponseDtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getGroup(@PathParam("id") long id) {
        Group group = service.get(id);
        return Response.ok().entity(GroupResponseDto.fromGroup(group)).build();
    }

    @POST
    @Path("")
    public Response create(CreateGroupRequestDto createGroupRequestDto) {
        Group group = new Group();
        group.setName(createGroupRequestDto.getName());
        group.setCreated(LocalDateTime.now());
        Group savedGroup = service.create(group);
        return Response.accepted().entity(GroupResponseDto.fromGroup(savedGroup)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateGroup(@PathParam("id") long id, CreateGroupRequestDto createGroupRequestDto) {
        Group group = service.get(id);
        if (group != null) {
            group.setName(createGroupRequestDto.getName());
            service.update(group);
        }
        return Response.ok().entity(GroupResponseDto.fromGroup(group)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGroup(@PathParam("id") long id){
        service.deleteById(id);
        return Response.ok().build();
    }
}