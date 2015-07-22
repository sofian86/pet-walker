package pl.petwalker.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import pl.petwalker.dto.PositionDto;

import javax.sql.DataSource;
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

        petWalkerService.registerLocation("jurek", new PositionDto(3.4444, 3.56565));
        petWalkerService.registerLocation("jurek", new PositionDto(4.222, 4.3333));
        petWalkerService.registerLocation("marek", new PositionDto(44.3434343, 9.432));

        final List<String> allUsers = petWalkerService.getAllUsers();
        assertThat(allUsers, notNullValue());
        assertThat(allUsers, hasItem("jurek"));
        assertThat(allUsers, hasItem("marek"));

        assertThat(petWalkerService.getLocations("jurek").size(), equalTo(2));
        assertThat(petWalkerService.getLocations("marek").size(), equalTo(1));
        assertThat(petWalkerService.getLocations("non").size(), equalTo(0));
    }

    private DataSource createDataSource() {
        return new EmbeddedDatabaseBuilder()
        .addScript("/sql/create-database.sql")
        .build();
    }

}
