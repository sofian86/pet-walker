package pl.petwalker.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import static org.apache.commons.lang3.Validate.notEmpty;

public class PetWalkerMain {

    public static void main(String[] args) throws Exception{
        final int port = determinePort();

        final Server server = new Server(port);
        final WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setWelcomeFiles(new String[]{"index.html"});
        root.setParentLoaderPriority(true);

        final String webappDirLocation = "web/src/main/webapp";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        server.setHandler(root);

        server.start();
        server.join();
    }

    private static int determinePort() {
        final String webPort = notEmpty(System.getenv("PORT"), "Environment variable PORT is not set");

        return Integer.parseInt(webPort);
    }

}
