package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class ReceivingThread extends Thread {

    private Socket socket;
    private String receivingString;
    private List<String> deletingWordList;

    public ReceivingThread(Socket socket, List<String> deletingWordList) {
        this.socket = socket;
        this.deletingWordList = deletingWordList;
    }

    @Override
    public void run() {

        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();


            int len;
            byte[] buf = new byte[128];

            while ((len = is.read(buf)) != -1) {
                //Delete:스트링을 삭제한다
                receivingString = new String(buf, 0, len);
                deletingWordList.add(receivingString);

                //제거 리스트를 내보내 준다.
            }


            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}