package io.thethelab;

import manager.SocketConnector;
import protobuf.Chat;

import java.io.IOException;

public class Main3 {
    public static void main(String[] args) throws IOException {

        Window.main("io.thethelab.Window");

        SocketConnector socketConnector = new SocketConnector("localhost", 5000);

        socketConnector.connect();

        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(Chat.Word word) {


                System.out.println("server: " + word);

                // 스트링에 Create:가 있으면 추가한다.
                // 스트링에 Delete:가 있으면 제거한다.
                WordData.refineRawString(word);

                if (WordData.deletingWordList.size() > WordData.pastDeleteWordListSize) {
                    System.out.println("hi");

//                    try {
//                        socketConnector.send(WordData.getSendingDeleteWord());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    WordData.pastDeleteWordListSize++;


                }
//                os.write(WordData.getSendingDeleteStrings().getBytes());
                // 보내고 바로 삭제해버리니 안됨
//                WordData.deletingWordList.clear();

            }
        });

        socketConnector.startReceiver();

        DataHelper.setSocketConnector(socketConnector);


    }
}
