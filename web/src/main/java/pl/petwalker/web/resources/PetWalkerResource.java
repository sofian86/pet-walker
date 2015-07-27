package pl.petwalker.web.resources;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.petwalker.dto.PositionDto;
import pl.petwalker.dto.UserTraceDto;
import pl.petwalker.web.DbUtils;
import pl.petwalker.web.PetWalkerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PetWalkerResource {

    private static final Logger LOG = LoggerFactory.getLogger(PetWalkerResource.class);

    //todo implement dependency injection
    private PetWalkerService petWalkerService;

    public PetWalkerResource() {
        this.petWalkerService = createPetWalkerService();
    }

    private static PetWalkerService createPetWalkerService() {
        final URI databaseUrl;
        try {
            databaseUrl = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        LOG.info("Connecting to " + databaseUrl);

        final BasicDataSource dataSource = DbUtils.dataSource(databaseUrl);

        return new PetWalkerService(dataSource);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Pet Walker FTW";
    }

    @GET
    @Path("users")
    public Response getUsers() {
        final List<String> users = petWalkerService.getAllUsers();
        return Response.ok(users).build();
    }

    @POST
    @Path("users/{username}")
    public Response registerUserLocation(@PathParam("username") String username, PositionDto position) {
        final UserTraceDto result = petWalkerService.registerLocation(username, position);
        return Response.status(201).entity(result).build();
    }

    @GET
    @Path("users/{username}")
    public Response getUserLocations(@PathParam("username") String username) {
        final List<UserTraceDto> positions = petWalkerService.getLocations(username);
        if (positions.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(positions).build();
    }

}
