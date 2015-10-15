package com.provectus.budgetrush.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.io.Resources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class JettyServer implements WebServer {

    private static int DEFAULT_PORT;
    private static int SECURE_PORT;
    private static String WEB_APP_ROOT;
    private static String CONTEXT_PATH;
    private static final String JAR_PATH = JettyServer.class.getProtectionDomain().getCodeSource().getLocation()
            .getPath();
    private static final String DIR_PATH = new File(JAR_PATH).getParent();
    private static final String KEY_STORE_PATH = Resources.getResource("my-release-key.keystore").toExternalForm();

    private Server jettyServer;

    public JettyServer() {

        getProperties();

    }

    public void getProperties() {

        try {
            Properties properties = new Properties();

            InputStream stream = Resources.getResource("app.properties").openStream();

            properties.load(stream);
            stream.close();
            DEFAULT_PORT = Integer.parseInt(properties.getProperty("server.default_port"));
            SECURE_PORT = Integer.parseInt(properties.getProperty("server.secure_port"));
            WEB_APP_ROOT = properties.getProperty("server.package_name");
            CONTEXT_PATH = properties.getProperty("server.context_path");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("File \"server.properties\" not found!");
        }
    }

    @Override
    public void start() {
        log.info(KEY_STORE_PATH);
        log.info(DIR_PATH);
        WebAppContext webAppContext = createContext();
        jettyServer = new Server();
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
        // webAppContext.setWar(DIR_PATH + WEB_APP_ROOT);
        // webAppContext.setWar("/home/taras/Budget_Rush/src/main/webapp");
        webAppContext.setWar(new File(DIR_PATH).getParent() + "\\src\\main\\webapp");
        return webAppContext;
    }

    private Connector[] createConnectors() {
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(SECURE_PORT);
        http_config.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(jettyServer, new HttpConnectionFactory(http_config));
        http.setPort(DEFAULT_PORT);
        http.setIdleTimeout(30000);

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(KEY_STORE_PATH);
        sslContextFactory.setKeyStorePassword("budgetrushprovectus");
        sslContextFactory.setKeyManagerPassword("budgetrushprovectus");

        HttpConfiguration https_config = new HttpConfiguration(http_config);
        https_config.addCustomizer(new SecureRequestCustomizer());

        ServerConnector https = new ServerConnector(jettyServer,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(https_config));
        https.setPort(SECURE_PORT);
        https.setIdleTimeout(500000);

        return new Connector[] { http, https };
    }

    @Override
    public void join() throws InterruptedException {
        jettyServer.join();
    }

}
