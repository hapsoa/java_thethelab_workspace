package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connector {

    private Socket socket;
    private String host;
    private int port;
    private InputStream is;
    private OutputStream os;

    public Connector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        //
        InetSocketAddress address = new InetSocketAddress(host, port);
        socket = new Socket();
        socket.connect(address);

        is = socket.getInputStream();
        os = socket.getOutputStream();

        //TODO
        new Thread(() -> {
            int len;
            byte[] bytes = new byte[128];
            try {
                while((len = is.read(bytes)) != -1) {
                    String s = new String(bytes, 0, len);
                    receiver.onReceive(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Receiver receiver;
    interface Receiver {
        void onReceive(String s);
    }
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void onDisconnect() {
        // 서버측에서 종료시켰을 때
    }

    public void disconnect() {

    }

    public void send() {

    }


}
