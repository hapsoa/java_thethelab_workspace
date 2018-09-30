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

    private volatile boolean isCancelled = false;

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
    }

    SocketConnector(Socket socket) {
        this.socket = socket;
    }


    void connect() throws IOException {

        InetSocketAddress socketAddress = new InetSocketAddress(host, port);

        socket.connect(socketAddress);
        is = socket.getInputStream();
        os = socket.getOutputStream();

        new Thread(() -> {

            int len;
            try {
                while (!isCancelled) {
                    byte[] readableBytes = new byte[2];

                    len = is.read(readableBytes, 0, 2);

                    int readableLength = ByteBuffer.wrap(readableBytes).getShort();

                    byte[] jsonObjectBytes = new byte[readableLength];
                    is.read(jsonObjectBytes);

                    if (len == -1) {
                        cancel();
                        receiver.onReceive("");
                    } else {
                        String s = new String(jsonObjectBytes);
                        receiver.onReceive(s);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    void send(String s) {

        int length = s.getBytes().length;

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes).put(s.getBytes())
                .array();
        try {
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
