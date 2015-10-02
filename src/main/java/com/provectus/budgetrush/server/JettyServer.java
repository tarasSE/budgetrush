package com.provectus.budgetrush.server;

import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

@Slf4j
class JettyServer implements WebServer {

    private static final int DEFAULT_PORT = 9090;

    private static final int SECURE_PORT = 9443;

    //private static final String HOST = "46.101.220.157";

    private static final String HOST = "localhost";

    private static final String JAR_PATH = JettyServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    private static final String DIR_PATH = new File(JAR_PATH).getParent();

    private static final String WEB_APP_ROOT = "/budgetrush-1.0.0-BUILD-SNAPSHOT.war/";

    private static final String CONTEXT_PATH = "/";

    private static final String keyStorePath = Resources.getResource("my-release-key.keystore").toExternalForm();

    private Server jettyServer;

    private int port;

    public JettyServer() {

        this.port = DEFAULT_PORT;
    }

    @Override
    public void start() {
        log.info(keyStorePath);
        Preconditions.checkState(port != 0, "Port is not specified");
        System.out.println();
        WebAppContext webAppContext = createContext();
        jettyServer = new Server(port);
        jettyServer.setConnectors(createConnectors());
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
        //webAppContext.setWar("/home/taras/Budget_Rush/src/main/webapp");

        return webAppContext;
    }

    private Connector[] createConnectors() {
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(SECURE_PORT);
        http_config.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(jettyServer, new HttpConnectionFactory(http_config));
        http.setHost(HOST);
        http.setPort(DEFAULT_PORT);
        http.setIdleTimeout(30000);

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(keyStorePath);
        sslContextFactory.setKeyStorePassword("budgetrushprovectus");
        sslContextFactory.setKeyManagerPassword("budgetrushprovectus");

        HttpConfiguration https_config = new HttpConfiguration(http_config);
        https_config.addCustomizer(new SecureRequestCustomizer());

        ServerConnector https = new ServerConnector(jettyServer, new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()), new HttpConnectionFactory(https_config));
        https.setHost(HOST);
        https.setPort(SECURE_PORT);
        https.setIdleTimeout(500000);

        return new Connector[]{http, https};
    }

    @Override
    public void join() throws InterruptedException {
        jettyServer.join();
    }

}
