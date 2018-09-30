package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class Acceptor extends Thread {
    private int port;

    Acceptor(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Game game = new Game();


        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket socket = serverSocket.accept();

                Session session = new Session(socket, game);
                session.connect();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
