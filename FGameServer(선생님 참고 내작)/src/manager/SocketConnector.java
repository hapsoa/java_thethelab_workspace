package manager;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class SocketConnector {

    private String host;
    private int port;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private Receiver receiver = s -> {
    };

    public volatile boolean isCancelled = false;

    public void cancel() {
        isCancelled = true;
    }

    public interface Receiver {
        void onReceive(String s);
    }

    public SocketConnector(String host, int port) {

        this.host = host;
        this.port = port;

        socket = new Socket();
        InetSocketAddress inetSocketAddress =
                new InetSocketAddress(host, port);

        try {
            socket.connect(inetSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SocketConnector(Socket socket) {

        this.socket = socket;

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void connect() {

        if (socket != null) {
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("not have socket");
        }

    }

    public void startReceiver() {

        new Thread(() -> {

            int len;
            int fullLen = 0;
            try {
                while (!isCancelled) {
                    // 처음 2바이트 읽어준다.
                    // 덜 읽으면 더 읽어주고, 넘치면 그 길이만큼만 읽어들인다.
                    byte[] readableBytes = new byte[2];
                    while (fullLen < 2) {
                        len = is.read(readableBytes, fullLen,
                                readableBytes.length - fullLen);
                        if (len != -1)
                            fullLen += len;
                        else {
                            cancel();
                            receiver.onReceive("");
                            break;
                        }

                    }
                    if (isCancelled)
                        break;

                    len = 0;
                    fullLen = 0;
                    int readableLength = ByteBuffer.wrap(readableBytes)
                            .getShort();
                    byte[] jsonObjectBytes = new byte[readableLength];
                    while (fullLen < readableLength) {
                        len = is.read(jsonObjectBytes, fullLen,
                                jsonObjectBytes.length - fullLen);
                        if (len != -1)
                            fullLen += len;
                        else
                            break;
                    }


                    if (len == 0) {

                    } else if (len != -1) {
                        String s = new String(jsonObjectBytes);
                        receiver.onReceive(s);
                    } else { // len == -1 일때
                        cancel();
                        receiver.onReceive("");
                    }
                }
            } catch (EOFException | SocketException ignored) {
                cancel();
                receiver.onReceive("");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    //TODO
    public void disconnect() {
    }

    //TODO
    public void onDisconnect() {
    }

    public void send(String msg) throws IOException {
        // 메세지 길이 + bytes[] 를 보낸다.

        int length = msg.getBytes().length;

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes)
                .put(msg.getBytes()).array();


        os.write(bytes);
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


    public static byte[] intToByte2Array(int value) {
        byte[] byteArray = new byte[2];
        byteArray[0] = (byte) (value >> 8);
        byteArray[1] = (byte) (value);
        return byteArray;
    }

    public static int byte2ArrayToInt(byte bytes[]) {
        return ((((int) bytes[0] & 0xff) << 8) |
                (((int) bytes[1] & 0xff)));
    }

    public byte[] concatenate(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        byte[] c = (byte[]) Array.newInstance(a.getClass().getComponentType(),
                aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }


}