package pl.petwalker.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.petwalker.test.model.Position;
import pl.petwalker.test.model.UserTrace;

import javax.sql.DataSource;
import java.net.URI;
import java.util.List;

import static org.apache.commons.lang3.Validate.notEmpty;

public class PetWalkerService {

    private final JdbcOperations jdbcOperations;

    public PetWalkerService(DataSource dataSource) {
        this.jdbcOperations = new JdbcTemplate(dataSource);
    }

    public List<String> getAllUsers() {
        return jdbcOperations.query("select distinct username from locations", (resultSet, rowNumber) -> {
            return resultSet.getString(1);
        });
    }

    public void registerLocation(String username, Position position) {
        notEmpty(username, "username cannot be empty");

        jdbcOperations.update("insert into locations (username, pos_time, latitude, longtitude) "
                + "values (?, now(), ?, ?)", username, position.getLatitude(), position.getLongtitude());
    }

    public List<UserTrace> getLocations(String username) {
        return jdbcOperations
                .query("select pos_time, latitude, longtitude from locations where username = ? order by pos_time",
                        new Object[]{username},
                        (resultSet, rowNumber) -> {
                            final UserTrace result = new UserTrace();
                            result.setTime(resultSet.getTimestamp(1));
                            final Position position = new Position();
                            position.setLatitude(resultSet.getDouble(2));
                            position.setLongtitude(resultSet.getDouble(3));
                            result.setPosition(position);
                            return result;
                        }
                );
    }

}