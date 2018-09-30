package io.thethelab;

import java.io.IOException;

public class Main3 {
    public static void main(String[] args) throws IOException {

        SocketConnector socketConnector = new SocketConnector("localhost", 5000);

        socketConnector.connect();

        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(String s) {


                System.out.println("server: " + s);

                // 스트링에 Create:가 있으면 추가한다.
                // 스트링에 Delete:가 있으면 제거한다.
//                WordData.refineRawString(rawString);
//
//                os.write(WordData.getSendingDeleteStrings().getBytes());
                // 보내고 바로 삭제해버리니 안됨
//                WordData.deletingWordList.clear();

            }
        });

        socketConnector.startReceiver();







        Connector connector = new Connector("localhost", 5000);

        connector.setReceiver(new Connector.Receiver() {
            @Override
            public void onReceive(String s) {
                System.out.println("good!");
            }
        });
        connector.connect();

    }
}
