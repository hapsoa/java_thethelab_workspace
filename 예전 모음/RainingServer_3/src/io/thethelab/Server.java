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


        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            // Main Thread
            System.out.println(socket.getRemoteSocketAddress());


            ConnectionThread connectionThread = new ConnectionThread(socket, wordList, deletingWordList);
            connectionThread.start();

        }
    }
}














