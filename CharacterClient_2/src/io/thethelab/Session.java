package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private volatile boolean isCancelled;
    private SessionListener listener;

    Session(String ip, int port) throws IOException {
        isCancelled = false;
        socket = new Socket(ip, port);
    }

    void setListener(SessionListener listener) {
        this.listener = listener;
    }

    void cancel() {
        isCancelled = true;
    }

    // Try with resource


    @Override
    public void run() {
        byte[] buf = new byte[128];
        try (InputStream is = socket.getInputStream()) {

            while(!isCancelled) {
                int len = is.read(buf);
                if (len == -1)
                    break;

                String message = new String(buf, 0, len);
                processMessage(message);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {
        // ADD,hello
        // DEL,hello
        if (listener == null)
            return;

        String[] packet = message.split(",");
        if (packet.length != 2) {
            System.out.println("wrong message");
            return;
        }

        String protocol = packet[0];
        String word = packet[1].trim();

        switch (protocol) {
            case "ADD" :
                listener.onAddWord(word);
                break;
            case "DEL" :
                listener.onRemoveWord(word);
                break;
        }

    }

}
