package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    public static void main(String[] args) throws Exception {

        List<String> wordList = Collections.synchronizedList(new ArrayList<>());

        wordList.add("hello");
        wordList.add("good morning");
        wordList.add("good afternoon");
        wordList.add("good night");
        wordList.add("high");
        wordList.add("fighting");
        wordList.add("urra");
        wordList.add("comfort");
        wordList.add("live");
        wordList.add("heart makes");

        List<String> deletingWordList = Collections.synchronizedList(new ArrayList<>());

//        BroadcastThread broadcastThread = new BroadcastThread();
//        broadcastThread.start();


        ServerSocket serverSocket = new ServerSocket(5000);

        List<SocketConnector> conns = Collections.synchronizedList(new ArrayList<>());

        CreateBroadcastThread createBroadcastThread =
                new CreateBroadcastThread(conns, wordList, deletingWordList);
        createBroadcastThread.start();

        while (true) {

            Socket socket = serverSocket.accept();
            // Main Thread
            System.out.println(socket.getRemoteSocketAddress());
//            broadcastThread.sockets.add(socket);




            SocketConnector socketConnector = new SocketConnector(socket);
//
            socketConnector.connect();

            socketConnector.setReceiver(new SocketConnector.Receiver() {
                @Override
                public void onReceive(String s) {
                    // Delete: 듣자마자, 모든 conn들에게 보낸다.
                    String[] rawStrings = s.split(",");

                    for (String str : rawStrings) {
                        if (!deletingWordList.contains(str)) // 문제 소지가 있음
                            deletingWordList.add(str);
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    for (String str : deletingWordList) {
                        stringBuilder.append(str);
                        stringBuilder.append(",");
                    }

                    if (stringBuilder.toString().length() > 0)
                        stringBuilder.deleteCharAt(stringBuilder.length()-1);
//                        stringBuilder.delete(stringBuilder.toString().length() - 1,
//                                stringBuilder.toString().length());
                    System.out.println(stringBuilder.toString());
                    try {
                        for (SocketConnector conn : conns) {
                            conn.send(stringBuilder.toString());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            socketConnector.startReceiver();

            createBroadcastThread.addingConns.add(socketConnector);



//            ConnectionThread connectionThread =
// new ConnectionThread(socket, wordList, deletingWordList);
//            connectionThread.start();


        }
    }






}














