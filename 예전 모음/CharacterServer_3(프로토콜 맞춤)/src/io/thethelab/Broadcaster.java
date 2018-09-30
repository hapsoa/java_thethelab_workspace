package io.thethelab;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Broadcaster extends Thread {

    private List<SocketConnector> conns;

    Broadcaster(List<SocketConnector> conns) {
        this.conns = conns;

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


}
