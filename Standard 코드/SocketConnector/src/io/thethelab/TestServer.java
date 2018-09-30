package io.thethelab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 5000);
        ServerSocket serverSocket = new ServerSocket(inetSocketAddress.getPort());

        while(true) {

            Socket socket = serverSocket.accept();

            SocketConnector socketConnector = new SocketConnector(socket);

            socketConnector.connect();

            List<String> strings = Arrays.asList("hello", "good", "bye", "night", "morning");

            for (String s : strings) {
                socketConnector.send(s);
                TimeUnit.SECONDS.sleep(1);
            }

        }


    }
}
