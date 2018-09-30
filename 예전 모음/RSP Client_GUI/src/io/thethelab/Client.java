package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        InetSocketAddress endpoint =
                new InetSocketAddress("localhost", 5000);
        socket.connect(endpoint);

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        int len;
        byte[] buf = new byte[1024];

        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {

            String line = scanner.nextLine();  // rock : "r", scissors : "s", paper : "p"
            os.write(line.getBytes()); //플레이어의 가위바위보를 보낸다.

//            Thread.sleep(1000);

            len = is.read(buf); // buf에 상대의 가위바위보와 승부결과  저장한다.

            if (len == -1)
                break;


            String s = new String(buf, 0, len);
//            String s = new String(buf);
            System.out.println("from server: " + s);



        }


        List<Integer> list = new ArrayList<>();

//        list.get(0).next

    }
}
