package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BroadcastThread extends Thread {

    public List<Socket> sockets = new ArrayList<>();

    List<String> wordList = Collections.synchronizedList(new ArrayList<>());

    List<String> deletingWordList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void run() {
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



        for (String s : wordList) {


            // 소켓마다 단어를 보낸다.
            for (Socket socket : sockets) {

                try (InputStream is = socket.getInputStream();
                     OutputStream os = socket.getOutputStream()) {

                    os.write(s.getBytes());
                    sleep(2000);

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }


    }
}
