package io.thethelab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestServer {
    public static void main(String[] args) {
        // write your code here
        Acceptor acceptor = new Acceptor(5000);
        acceptor.start();
    }
}
