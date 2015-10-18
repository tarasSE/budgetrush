package com.provectus.budgetrush.server;

interface WebServer {

    public void start(StartArgs starType);

    public void join() throws InterruptedException;

}
