package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ConnectionThread extends Thread {

    private Socket socket;

    public ConnectionThread(Socket socket) {
        this.socket = socket;
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


        byte[] buf = new byte[128];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                // len: 수신된 바이트의 크기
                // len == -1: EOF, 연결이 종료되었다.
                os.write(buf, 0, len);
            }

            is.close();
            os.close();

        } catch (IOException e) {
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














