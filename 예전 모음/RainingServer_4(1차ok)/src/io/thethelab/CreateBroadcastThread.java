package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateBroadcastThread extends Thread {

    private List<SocketConnector> conns;
    private List<String> wordList;
    private List<String> deletingWordList;

    List<SocketConnector> addingConns = new ArrayList<>();

    public CreateBroadcastThread(List<SocketConnector> conns,
                                 List<String> wordList, List<String> deletingWordList) {
        this.conns = conns;
        this.wordList = wordList;
        this.deletingWordList = deletingWordList;
    }

    @Override
    public void run() {
        while(true) {
            for (String s : wordList) {
                // 소켓마다 단어를 보낸다.
                try {
                    sleep(2000);
                    for (SocketConnector conn : conns) {

                        conn.send("Create:" + s);

                    }
                }catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                conns.addAll(addingConns);
                addingConns.clear();
            }
        }
    }


}
