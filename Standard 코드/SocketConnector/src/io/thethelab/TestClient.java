package io.thethelab;

import java.io.IOException;

public class TestClient {

    public static void main(String[] args) throws IOException {
	// write your code here
        SocketConnector socketConnector = new SocketConnector("localhost", 5000);

        socketConnector.connect();

        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(String s) {

                System.out.println(s);

            }
        });

        socketConnector.startReceiver();

    }
}
