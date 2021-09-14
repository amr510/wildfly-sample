package explaineverything.wildfly.rest.service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import explaineverything.wildfly.ejb.UserEJB;
import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.model.User;
import explaineverything.wildfly.rest.model.CreateGroupRequestDto;
import explaineverything.wildfly.rest.model.CreateUserRequestDto;
import explaineverything.wildfly.rest.model.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	@Inject
	private UserEJB service;


	@GET
	@Path("")
	public Response getAllUsers() {
		List<User> users = service.getAll();

		List<UserResponseDto> userResponseDtos = users.stream()
				.map(UserResponseDto::fromUser)
				.collect(Collectors.toList());
		return Response.ok().entity(userResponseDtos).build();
	}

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") long id) {
		User user = service.get(id);
		return Response.ok()
				.entity(UserResponseDto.fromUser(user))
				.build();
	}

	@POST
	@Path("")
	public Response create(CreateUserRequestDto createUserRequestDto) {
		User user = new User();
		user.setName(createUserRequestDto.getName());
		user.setEmail(createUserRequestDto.getEmail());
		user.setType(createUserRequestDto.getType());
		user.setCreated(LocalDateTime.now());
		User savedUser = service.create(user);
		return Response.accepted().entity(UserResponseDto.fromUser(savedUser)).build();
	}

	@PUT
	@Path("{id}")
	public Response updateUser(@PathParam("id") long id, CreateUserRequestDto updateUserRequestDto) {
		User user = service.get(id);
		if (user != null) {
			user.setName(updateUserRequestDto.getName());
			user.setEmail(updateUserRequestDto.getEmail());
			user.setType(updateUserRequestDto.getType());
			service.update(user);
		}
		return Response.ok().entity(UserResponseDto.fromUser(user)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") long id) {
		User user = service.deleteById(id);
		return Response.ok().entity(UserResponseDto.fromUser(user)).build();
	}

}