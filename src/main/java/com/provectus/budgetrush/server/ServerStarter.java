package com.provectus.budgetrush.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerStarter {

    public static void main(String[] args) {
        try {

            WebServer server = new JettyServer();


            if (args[0].equals("-s")) {
                server.start(StartArgs.NORMAL);
            } else if (args[0].equals("--test")) {
                server.start(StartArgs.TEST);
            }


            log.info("Server started");

            server.join();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Use -s or --test options to start");
        } catch (Exception exception) {
            log.error("Failed to start server.", exception);
        }

    }

}
