package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ConnectionThread extends Thread {

    private Socket socket;

    private List<String> wordList = new ArrayList<>();

    public ConnectionThread(Socket socket) {
        this.socket = socket;
        wordList.add("Hello");
        wordList.add("GoodMorning");
        wordList.add("GoodAfternoon");
        wordList.add("Goodnight");
        wordList.add("High");
        wordList.add("Fighting");
        wordList.add("Urra!!");
        wordList.add("Comfort");
        wordList.add("Live");
        wordList.add("Heartmakes");
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
//            while(true) {
//                os.write();
//            }
            for (String word : wordList) {
                os.write(word.getBytes());
                sleep(2000);
            }

            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("종료되었습니다.  " + socket.getRemoteSocketAddress());
    }
}



public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            // Main Thread
            System.out.println(socket.getRemoteSocketAddress());




            ConnectionThread connectionThread = new ConnectionThread(socket);
            connectionThread.start();

        }
    }
}

/*
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        // 클라이언트가 접속을 요청하면, 수락한다.
        // : 세션이 형성된다.
        Socket socket = serverSocket.accept();
        System.out.println(socket.getRemoteSocketAddress());
    }
}
*/














