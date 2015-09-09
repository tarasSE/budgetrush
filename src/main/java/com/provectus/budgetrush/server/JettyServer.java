package com.provectus.budgetrush.server;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.utils.RootConfig;
import com.provectus.budgetrush.utils.WebAppConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource("classpath:app.properties")
class JettyServer implements WebServer {

    private static final int DEFAULT_PORT = 8080;

    private static final String CONTEXT_PATH = "/";

    private static final String MAPPING_URL = "/*";

    private static final String SERVLET_NAME = "appServlet";

    private Server jettyServer;

    private int port;

    public JettyServer() {

        this.port = DEFAULT_PORT;
    }

    @Override
    public void start() {
        Preconditions.checkState(port != 0, "Port is not specified");

        jettyServer = new Server(port);
        jettyServer.setHandler(createContextHandler());

        try {
            jettyServer.start();
        } catch (Exception exception) {
            log.error("Failed to start server", exception);
            throw new RuntimeException();
        }

        log.info("Server started...");
    }

    private ServletContextHandler createContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);

        AnnotationConfigWebApplicationContext contextConfigLocation = new AnnotationConfigWebApplicationContext();
        contextConfigLocation.register(RootConfig.class);
        contextHandler.addEventListener(new ContextLoaderListener(contextConfigLocation));

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebAppConfig.class);
        Servlet servlet = new DispatcherServlet(webContext);
        ServletHolder servletHolder = new ServletHolder(SERVLET_NAME, servlet);
        contextHandler.addServlet(servletHolder, MAPPING_URL);

        return contextHandler;

    }

    @Override
    public void join() throws InterruptedException {
        jettyServer.join();
    }

}
