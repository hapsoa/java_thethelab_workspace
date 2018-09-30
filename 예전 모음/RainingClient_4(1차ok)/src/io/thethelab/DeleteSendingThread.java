package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DeleteSendingThread extends Thread {

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
            byte[] buf = new byte[128];

            while ((len = is.read(buf)) != -1) {

                // if 삭제할 단어가 생기면, 삭제할 단어를 보낸다.(write)
//                os.write();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
