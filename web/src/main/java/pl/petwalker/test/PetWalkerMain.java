package pl.petwalker.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.petwalker.test.model.Position;
import pl.petwalker.test.model.UserTrace;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;
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
        final BasicDataSource dataSource = dataSource(databaseUrl);

        final PetWalkerService petWalkerService = new PetWalkerService(dataSource);

        final ObjectMapper objectMapper = new ObjectMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        get("/users", (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return objectWriter.writeValueAsString(petWalkerService.getAllUsers());
        });

        post("/users/:username", (request, response) -> {
            final Position position = objectMapper.readValue(request.body(), Position.class);
            final UserTrace result = petWalkerService.registerLocation(request.params(":username"), position);
            response.status(201);
            response.type(CONTENT_TYPE_JSON);
            return objectMapper.writeValueAsString(result);
        });

        get("/users/:username", (req, response) -> {
            final List<UserTrace> positions = petWalkerService.getLocations(req.params(":username"));
            if (positions.isEmpty()) {
                response.status(404);
                return "User does not exist";
            }
            response.type(CONTENT_TYPE_JSON);
            return objectWriter.writeValueAsString(positions);
        });
    }

    public static BasicDataSource dataSource(URI dbUri) {
        notNull(dbUri, "dbUri is null");

        final String userInfo = notNull(dbUri.getUserInfo(), "userInfo is null in db uri: %s", dbUri);

        String username = userInfo.split(":")[0];
        String password = userInfo.split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        LOG.info("Connecting to " + dbUrl);

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }


}
