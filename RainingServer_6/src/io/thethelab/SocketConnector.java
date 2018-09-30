package io.thethelab;

import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.Chat;

import java.io.*;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
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
    private Receiver receiver = s -> {
    };

    public volatile boolean isCancelled = false;

    public void cancel() {
        isCancelled = true;
    }

    public interface Receiver {
        void onReceive(Chat.Word word);
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

                    } else if (len != -1) { // 결과물 처리
                        Chat.Word word = Chat.Word.parseFrom(jsonObjectBytes);
                        System.out.println(word);
                        receiver.onReceive(word);
                    } else { // len == -1 일때
                        cancel();
                    }
                }
            } catch (SocketException e) {
                cancel();
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

    public void send(String msg) {
        // 메세지 길이 + bytes[] 를 보낸다.

        int length = msg.getBytes().length;

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes)
                .put(msg.getBytes()).array();


        try {
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(Chat.Word word) throws IOException {
        // 메세지 길이 + bytes[] 를 보낸다.
        int length = word.getSerializedSize();

        byte[] lengthBytes = ByteBuffer.allocate(2).putShort((short) length).array();

        byte[] bytes = ByteBuffer.allocate(2 + length).put(lengthBytes)
                .put(word.toByteArray()).array();

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


}