package com.provectus.budgetrush.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerStarter {

    public static void main(String[] args) {
        try {

            WebServer server = new JettyServer();

            server.start(getStartType(args));
            log.info("Server started");

            server.join();

        } catch (Exception exception) {
            log.error("Failed to start server.", exception);
        }

    }

    private static StartArgs getStartType(String[] args) {
        if (args != null && args.length > 0 && (args[0].equals("-t") || args[0].equals("--test"))) {
            log.info("Start type is 'TEST'");
            return StartArgs.TEST;
        }
        return StartArgs.NORMAL;

    }
}
