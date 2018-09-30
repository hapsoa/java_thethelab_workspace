package io.thethelab;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        // 클라이언트가 접속을 요청하면, 수락한다.
        // : 세션이 형성된다.
        Socket socket = serverSocket.accept();
        System.out.println(socket.getRemoteSocketAddress());

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        byte[] buf = new byte[128];
        int len;
        while((len = is.read(buf)) != -1) {
            // len : 수신된 바이트의 크기
            // len == -1 : EOF, 연결이 종료되었다.
            System.out.println(new String(buf, 0, len));
            os.write(buf, 0, len);
        }

        System.out.println("종료되었습니다.");

    }
}
