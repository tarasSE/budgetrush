package com.provectus.budgetrush.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerStarter {

    public static void main(String[] args) {
        try {
            WebServer server = new JettyServer();
            server.start();

            log.info("Server started");

            server.join();

        } catch (Exception exception) {
            log.error("Failed to start server.", exception);
        }

    }

}
