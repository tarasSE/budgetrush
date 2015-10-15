package com.provectus.budgetrush.server;

interface WebServer {

    public void start(StartArgs startArgs);

    public void join() throws InterruptedException;

}
