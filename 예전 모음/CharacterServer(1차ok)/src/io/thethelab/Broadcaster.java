package io.thethelab;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Broadcaster extends Thread {

    private List<String> words;
    private List<Session> sessions;

    private List<String> characterData;
    private List<SocketConnector> conns;

    Broadcaster(List<SocketConnector> conns) {
        this.conns = conns;

    }

    private void addWord(String word) {
        broadcast("ADD," + word);
    }

    void removeWord(String word) {
//        if (!words.contains(word)) {
//            return;
//        }

        broadcast("DEL," + word);
    }

    public void broadcast(String message) {
        for (SocketConnector conn : conns) {
            try {
                conn.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);

                int index = random.nextInt(words.size());
                addWord(words.get(index));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
