import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ServerSocketConnector {

    private List<SocketConnector> clientSocketConnectorList;
    ServerSocket serverSocket;
    private InputStream is;
    private Receiver receiver;
    private volatile boolean isCancelled = false;

    ServerSocketConnector(int port) {
        clientSocketConnectorList = new CopyOnWriteArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface Receiver {
        void onReceive(String s, SocketConnector socketConnector);
    }

    void add(SocketConnector socketConnector) { clientSocketConnectorList.add(socketConnector); }

    void connect(SocketConnector socketConnector) throws IOException {

        is = socketConnector.socket.getInputStream();

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
                        receiver.onReceive("", socketConnector);
                    } else {
                        String s = new String(jsonObjectBytes);
                        receiver.onReceive(s, socketConnector);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void cancel() {
        isCancelled = true;
    }

    void send(String s) {
        int length = s.getBytes().length;
        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();
        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes).put(s.getBytes())
                .array();



        for (SocketConnector socketConnector : clientSocketConnectorList) {
            try {
                OutputStream os = socketConnector.socket.getOutputStream();
                os.write(bytes);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

}
