package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordReceivingClient extends Thread {



    private String rawString;

    public WordReceivingClient() {

    }

    @Override
    public void run() {


        Socket socket = new Socket();

        InetSocketAddress endpoint =
                new InetSocketAddress("localhost", 5000);
        try {
            socket.connect(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream os = null;
        InputStream is = null;
        try {
            os = socket.getOutputStream();
            is = socket.getInputStream();


            int len;
            byte[] buf = new byte[1024];

            while ((len = is.read(buf)) != -1) {
                rawString = new String(buf, 0, len);

                System.out.println("server: " + rawString);

                // 스트링에 Create:가 있으면 추가한다.
                // 스트링에 Delete:가 있으면 제거한다.
                WordData.refineRawString(rawString);

                os.write(WordData.getSendingDeleteStrings().getBytes());
                // 보내고 바로 삭제해버리니 안됨
//                WordData.deletingWordList.clear();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
