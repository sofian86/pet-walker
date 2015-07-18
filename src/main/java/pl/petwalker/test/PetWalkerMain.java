package pl.petwalker.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp.BasicDataSource;
import pl.petwalker.test.model.Position;
import pl.petwalker.test.model.UserTrace;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class PetWalkerMain {

    public static void main(String[] args) throws URISyntaxException, SQLException {
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/", (req, res) -> "Pet Walker FTW");

        final URI databaseUrl = new URI(System.getenv("DATABASE_URL"));
        final BasicDataSource dataSource = dataSource(databaseUrl);

        final PetWalkerService petWalkerService = new PetWalkerService(dataSource);

        final ObjectMapper objectMapper = new ObjectMapper();
        get("/users", (request, response) -> objectMapper.writeValueAsString(petWalkerService.getAllUsers()));

        post("/users/:username", (request, response) -> {
            final Position position = objectMapper.readValue(request.body(), Position.class);
            petWalkerService.registerLocation(request.params(":username"), position);
            response.status(201);
            return "";
        });

        get("/users/:username", (req, res) -> {
            final List<UserTrace> positions = petWalkerService.getLocations(req.params(":username"));
            if (positions.isEmpty()) {
                res.status(404);
                return "User does not exist";
            }
            return objectMapper.writeValueAsString(positions);
        });
    }

    public static BasicDataSource dataSource(URI dbUri) {
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }


}
