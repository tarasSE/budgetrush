package com.provectus.budgetrush.server;

interface WebServer {

    public void start();

    public void join() throws InterruptedException;

}
