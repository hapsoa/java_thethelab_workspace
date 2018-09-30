package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketConnector {

    private String host;
    private int port;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private Receiver receiver = s -> {};

    public volatile boolean isCancelled = false;

    public void cancel() {
        isCancelled = true;
    }

    public interface Receiver {
        void onReceive(String s);
    }

    public SocketConnector(String host, int port) throws IOException {

        this.host = host;
        this.port = port;

        socket = new Socket();
        InetSocketAddress inetSocketAddress =
                new InetSocketAddress(host, port);
        socket.connect(inetSocketAddress);

    }

    public SocketConnector(Socket socket) throws IOException {

        this.socket = socket;

        is = socket.getInputStream();
        os = socket.getOutputStream();

    }

    public void connect() throws IOException {

//        if (socket == null) {
        is = socket.getInputStream();
        os = socket.getOutputStream();
//        }

    }

    public void startReceiver() {

        new Thread(() -> {

            int len;
            byte[] bytes = new byte[1024];
            try {
                while(!isCancelled) {
                    len = is.read(bytes);
                    if (len == -1) {
                        cancel();
                        receiver.onReceive("");
                    }
                    else {
                        String s = new String(bytes, 0, len);
                        receiver.onReceive(s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    //TODO
    public void disconnect() {}
    //TODO
    public void onDisconnect() {}

    public void send(String msg) throws IOException {
        os.write(msg.getBytes());
        os.flush();
    }



    // 연결된 대상이 보낸 메세지를 수신 할 수 있어야 한다.
    // 수동적이므로 인터페이스로 등록을 한다.
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    //다른 소켓으로 연결
    public void setConnection() {

    }


    // broadcast()는 모든 클라이언트들에게 send()해주는 메소드
    // List<Socket> conns
    // 뭐 할 때마다 broadcast()한다.
}
