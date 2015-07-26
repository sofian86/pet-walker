package pl.petwalker.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.petwalker.dto.PositionDto;
import pl.petwalker.dto.UserTraceDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class PetWalkerMain {

    private static final Logger LOG = LoggerFactory.getLogger(PetWalkerMain.class);
    private static final String CONTENT_TYPE_JSON = "application/json";

    public static void main(String[] args) throws URISyntaxException, SQLException {
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/", (req, res) -> "Pet Walker FTW");

        final URI databaseUrl = new URI(System.getenv("DATABASE_URL"));
        LOG.info("Connecting to " + databaseUrl);

        final BasicDataSource dataSource = DbUtils.dataSource(databaseUrl);

        final PetWalkerService petWalkerService = new PetWalkerService(dataSource);

        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        get("/users", (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return objectWriter.writeValueAsString(petWalkerService.getAllUsers());
        });

        post("/users/:username", (request, response) -> {
            final PositionDto position = objectMapper.readValue(request.body(), PositionDto.class);
            final UserTraceDto result = petWalkerService.registerLocation(request.params(":username"), position);
            response.status(201);
            response.type(CONTENT_TYPE_JSON);
            return objectMapper.writeValueAsString(result);
        });

        get("/users/:username", (req, response) -> {
            final List<UserTraceDto> positions = petWalkerService.getLocations(req.params(":username"));
            if (positions.isEmpty()) {
                response.status(404);
                return "User does not exist";
            }
            response.type(CONTENT_TYPE_JSON);
            return objectWriter.writeValueAsString(positions);
        });
    }

}
