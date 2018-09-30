package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ConnectionThread extends Thread {

    private Socket socket;
    private List<String> wordList;
    private List<String> deletingWordList;

    private int i = 0;


    public ConnectionThread(Socket socket, List<String> wordList, List<String> deletingWordList) {
        this.socket = socket;
        this.wordList = wordList;
        this.deletingWordList = deletingWordList;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();


            while(i < wordList.size()) {

                StringBuilder stringBuilder = new StringBuilder();
                String sendingString;

                // Create: 문자열과 Delete: 문자열을 만든다.
                stringBuilder.append("Create:");
                stringBuilder.append(wordList.get(i));
                stringBuilder.append(",");
                for (String s : deletingWordList) {
//                    stringBuilder.append("Delete:");
                    stringBuilder.append(s);
                    stringBuilder.append(",");
                }
                stringBuilder.delete(stringBuilder.toString().length() - 1, stringBuilder.toString().length());

                os.write(stringBuilder.toString().getBytes()); //WRITE!!
                i = (i + 1) % wordList.size();
                sleep(2000);


                // 한 클라이언트에서 제거 명령을 받으면,
                // 모든 클라이언트에서 그 단어를 제거시킨다.

                // 삭제할 데이터들을 저장한다
                int len = 0;
                byte[] buf = new byte[1024];


                assert is != null;
                // 읽을게 없으면 그냥 다음으로 넘기는 방법이 없을까
                if (is.available() != 0) {
                    len = is.read(buf); //READ!!

                    String deleteRawString = new String(buf, 0, len);

                    String[] tempStrings = deleteRawString.split(",");

                    // 여기서 수정이 필요
                    deletingWordList.clear();
                    deletingWordList.addAll(Arrays.asList(tempStrings));
                }



            }


            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("종료되었습니다.  " + socket.getRemoteSocketAddress());
    }




}
