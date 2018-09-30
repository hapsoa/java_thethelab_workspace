package io.thethelab;

import java.io.*;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    // 처음 2바이트 읽어준다.
                    // 덜 읽으면 더 읽어주고, 넘치면 그 길이만큼만 읽어들인다.
                    byte[] readableBytes = new byte[2];
//                    while(readableBytes[1] != null)
                    len = is.read(readableBytes);

                    int readableLength = ByteBuffer.wrap(readableBytes)
                            .getShort();

                    byte[] jsonObjectBytes = new byte[readableLength];
                    is.read(jsonObjectBytes);


                    if (len == -1) {
                        cancel();
                        receiver.onReceive("");
                    }
                    else {
                        String s = new String(jsonObjectBytes);
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
        // 메세지 길이 + bytes[] 를 보낸다.

        int length = msg.getBytes().length;

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short)length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes).put(msg.getBytes())
                .array();

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
        byteArray[0] = (byte)(value >> 8);
        byteArray[1] = (byte)(value);
        return byteArray;
    }

    public static int byte2ArrayToInt(byte bytes[]) {
        return ((((int)bytes[0] & 0xff) << 8) |
                (((int)bytes[1] & 0xff)));
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