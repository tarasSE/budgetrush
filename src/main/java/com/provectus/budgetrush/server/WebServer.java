package com.provectus.budgetrush.server;

interface WebServer {

    void start(StartArgs starType);

    void join() throws InterruptedException;

}
