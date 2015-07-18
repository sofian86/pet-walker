package pl.petwalker.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import pl.petwalker.test.model.Position;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class PetWalkerServiceTest {

    @Test
    public void testCreation() throws URISyntaxException, JsonProcessingException {
        final PetWalkerService petWalkerService = new PetWalkerService(createDataSource());

        petWalkerService.registerLocation("jurek", new Position(3.4444, 3.56565));
        petWalkerService.registerLocation("jurek", new Position(4.222, 4.3333));
        petWalkerService.registerLocation("marek", new Position(44.3434343, 9.432));

        final List<String> allUsers = petWalkerService.getAllUsers();
        assertThat(allUsers, notNullValue());
        assertThat(allUsers, hasItem("jurek"));
        assertThat(allUsers, hasItem("marek"));

        assertThat(petWalkerService.getLocations("jurek").size(), equalTo(2));
        assertThat(petWalkerService.getLocations("marek").size(), equalTo(1));
        assertThat(petWalkerService.getLocations("non").size(), equalTo(0));

        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        System.out.println(objectWriter.writeValueAsString(new Position(3.4444, 3.56565)));
//        System.out.println(objectWriter.writeValueAsString(petWalkerService.getLocations("jurek")));
    }

    private DataSource createDataSource() {
        return new EmbeddedDatabaseBuilder()
        .addScript("/sql/create-database.sql")
        .build();
    }


}