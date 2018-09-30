package io.thethelab;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Acceptor extends Thread {
    private int port;

    // ArrayList 모든 연산이 스레드 안전하게 동작하는 것을 보장한다.
    private List<SocketConnector> conns = new CopyOnWriteArrayList<>();

    Acceptor(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Broadcaster broadcaster = new Broadcaster(conns);
//            broadcaster.start();

            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket socket = serverSocket.accept();

                SocketConnector socketConnector = new SocketConnector(socket);
                conns.add(socketConnector);

                socketConnector.connect();


                try {
                    socketConnector.send(Utils.getAcceptMessage().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                socketConnector.setReceiver(new SocketConnector.Receiver() {
                    @Override
                    public void onReceive(String s) {
                        System.out.println(s);
                        if (socketConnector.isCancelled) {
                            conns.remove(socketConnector);
                        }
                        else {

                            broadcaster.broadcast(s);
                        }

                    }
                });
                socketConnector.startReceiver();



            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
