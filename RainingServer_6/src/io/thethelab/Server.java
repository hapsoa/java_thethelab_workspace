package io.thethelab;

import protobuf.Chat;

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


        ServerSocket serverSocket = new ServerSocket(5000);

        List<SocketConnector> conns = Collections.synchronizedList(new ArrayList<>());

        CreateBroadcastThread createBroadcastThread =
                new CreateBroadcastThread(conns, wordList, deletingWordList);
        createBroadcastThread.start();

        while (true) {

            Socket socket = serverSocket.accept();
            // Main Thread
            System.out.println(socket.getRemoteSocketAddress());


            SocketConnector socketConnector = new SocketConnector(socket);

            socketConnector.connect();

            socketConnector.setReceiver(new SocketConnector.Receiver() {
                @Override
                public void onReceive(Chat.Word word) {
                    if (!socketConnector.isCancelled) {

                        System.out.println("Yap");
                        // Delete: 듣자마자,
                        if (word.getType().equals("Delete") &&
                                !deletingWordList.contains(word.getWord()))
                            deletingWordList.add(word.getWord());

                        // 모든 conn들에게 보낸다.
                        for (SocketConnector conn : conns) {

                            try {
                                conn.send(word);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    } else {
                        conns.remove(socketConnector);
                    }

                }
            });

            socketConnector.startReceiver();

            createBroadcastThread.addingConns.add(socketConnector);


        }
    }


}














