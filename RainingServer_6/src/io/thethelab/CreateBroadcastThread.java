package io.thethelab;

import protobuf.Chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateBroadcastThread extends Thread {

    private List<SocketConnector> conns;
    private List<String> wordList;
    private List<String> deletingWordList;

    List<SocketConnector> addingConns = new ArrayList<>();

    private List<SocketConnector> deletingConns = new ArrayList<>();

    public CreateBroadcastThread(List<SocketConnector> conns,
                                 List<String> wordList, List<String> deletingWordList) {
        this.conns = conns;
        this.wordList = wordList;
        this.deletingWordList = deletingWordList;
    }

    @Override
    public void run() {
        while (true) {
            for (String s : wordList) {
                // 소켓마다 단어를 보낸다.
                try {
//                    sleep(2000); //구시대 것
                    TimeUnit.SECONDS.sleep(2);


                    for (SocketConnector conn : conns) {

//                        conn.send("Create:" + s);
                        //TODO remake
                        Chat.Word creatingWord = Chat.Word.newBuilder()
                                .setType("Create")
                                .setWord(s)
                                .build();


                        conn.send(creatingWord);
                        System.out.println(creatingWord);


                    }


                } catch (SocketException e) {
                    conns.remove(deletingConns);
                    deletingConns.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                conns.addAll(addingConns);
                addingConns.clear();
            }
        }
    }


}
