import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

class SocketConnector {

    private String host;
    private int port;
    private InputStream is;
    private OutputStream os;
    private Receiver receiver;
    Socket socket;

    volatile boolean isCancelled = false;

    private void cancel() {
        isCancelled = true;
    }

    interface Receiver {
        void onReceive(String s);
    }

    SocketConnector(String host, int port) {
        this.host = host;
        this.port = port;
        socket = new Socket();
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        try {
            socket.connect(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SocketConnector(Socket socket) {
        this.socket = socket;
    }

    void connect() {
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void startReceiver(){
        new Thread(() -> {

            int len = 0;
            int fullLen = 0;
            byte[] bytes = new byte[1024];
            try {
                while(!isCancelled) {
                    byte[] readableBytes = new byte[2];
                    while(fullLen < 2) {
                        len = is.read(readableBytes, fullLen, readableBytes.length-fullLen );
                        if(len != -1)
                            fullLen += len;
                        else
                            break;
                    }

                    len = 0;
                    fullLen = 0;
                    int readableLength = ByteBuffer.wrap(readableBytes)
                            .getShort();
                    byte[] jsonObjectBytes = new byte[readableLength];
                    while(fullLen < readableLength) {
                        len = is.read(jsonObjectBytes, fullLen, jsonObjectBytes.length-fullLen);
                        if(len != -1)
                            fullLen += len;
                        else
                            break;
                    }


                    if (len == 0) {

                    }
                    else if (len != -1) {
                        String s = new String(jsonObjectBytes);
                        receiver.onReceive(s);
                    }
                    else {
                        cancel();
                        receiver.onReceive("");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }




    void send(String s) {

        int length = s.getBytes().length;

        System.out.println("SEND!!");

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes).put(s.getBytes())
                .array();
        try {
            System.out.println(s);
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

}
