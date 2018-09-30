package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
        byte[] buf = new byte[128];

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            os.write(line.getBytes());
            len = is.read(buf);

            if (len == -1)
                break;

            String s = new String(buf, 0, len);
            System.out.println("from server: " + s);
        }

    }
}
