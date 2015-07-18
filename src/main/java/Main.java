import com.heroku.sdk.jdbc.DatabaseUrl;
import org.jscience.physics.amount.Amount;
import org.jscience.physics.model.RelativisticModel;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import static javax.measure.unit.SI.KILOGRAM;

public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/hello", (req, res) -> {
            RelativisticModel.select();
            final String energy = System.getenv().get("ENERGY");
            final Amount<Mass> m = Amount.valueOf(energy).to(KILOGRAM);
            return "E=mc^2: 12 GeV = " + m;
        });

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        get("/db", (req, res) -> {
            Connection connection = null;
            Map<String, Object> attributes = new HashMap<>();
            try {
                connection = DatabaseUrl.extract().getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
                stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

                ArrayList<String> output = new ArrayList<String>();
                while (rs.next()) {
                    output.add("Read from DB: " + rs.getTimestamp("tick"));
                }

                attributes.put("results", output);
                return new ModelAndView(attributes, "db.ftl");
            } catch (Exception e) {
                attributes.put("message", "There was an error: " + e);
                return new ModelAndView(attributes, "error.ftl");
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }, new FreeMarkerEngine());

    }

}
