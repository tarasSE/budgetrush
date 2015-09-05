package com.provectus.budgetrush.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.annotation.PropertySource;

import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource("classpath:app.properties")
class JettyServer implements WebServer {

    private static final int DEFAULT_PORT = 8080;

    private static final String WEB_APP_ROOT = "src/main/webapp";

    private static final String CONTEXT_PATH = "/";

    private Server jettyserver;

    private int port;

    public JettyServer() {

        this.port = DEFAULT_PORT;
    }

    @Override
    public void start() {
        Preconditions.checkState(port != 0, "Port is not specified");

        WebAppContext webAppContext = createContext();
        jettyserver = new Server(port);
        jettyserver.setHandler(webAppContext);

        try {
            jettyserver.start();
        } catch (Exception exception) {
            log.error("Failed to start server", exception);
            throw new RuntimeException();
        }

        log.info("Server started...");
    }

    private WebAppContext createContext() {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath(CONTEXT_PATH);
        webAppContext.setWar(WEB_APP_ROOT);
        return webAppContext;
    }

    @Override
    public void join() throws InterruptedException {
        jettyserver.join();
    }

}
