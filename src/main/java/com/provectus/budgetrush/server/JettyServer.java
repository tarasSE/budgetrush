package com.provectus.budgetrush.server;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

@Slf4j
class JettyServer implements WebServer {

    private static final int DEFAULT_PORT = 8080;

    private static final String JAR_PATH = JettyServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String DIR_PATH = new File(JAR_PATH).getParent();

    private static final String WEB_APP_ROOT = "/budgetrush-1.0.0-BUILD-SNAPSHOT.war/";

    private static final String CONTEXT_PATH = "/";

    private Server jettyServer;

    private int port;

    public JettyServer() {

        this.port = DEFAULT_PORT;
    }

    @Override
    public void start() {
        Preconditions.checkState(port != 0, "Port is not specified");

        WebAppContext webAppContext = createContext();
        jettyServer = new Server(port);
        jettyServer.setHandler(webAppContext);

        try {
            jettyServer.start();
        } catch (Exception exception) {
            log.error("Failed to start server", exception);
            throw new RuntimeException();
        }

        log.info("Server started...");
    }

    private WebAppContext createContext() {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath(CONTEXT_PATH);
        webAppContext.setWar(DIR_PATH + WEB_APP_ROOT);
        return webAppContext;
    }

    @Override
    public void join() throws InterruptedException {
        jettyServer.join();
    }

}
