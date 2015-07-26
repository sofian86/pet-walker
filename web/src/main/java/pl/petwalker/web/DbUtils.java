package pl.petwalker.web;

import org.apache.commons.dbcp.BasicDataSource;

import java.net.URI;

import static org.apache.commons.lang3.Validate.notNull;

public final class DbUtils {

    private DbUtils() {
    }

    public static BasicDataSource dataSource(URI dbUri) {
        notNull(dbUri, "dbUri is null");

        final String userInfo = notNull(dbUri.getUserInfo(), "userInfo is null in db uri: %s", dbUri);

        String username = userInfo.split(":")[0];
        String password = userInfo.split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
