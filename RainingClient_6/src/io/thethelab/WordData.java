package io.thethelab;

import manager.View;
import protobuf.Chat;

import java.util.ArrayList;
import java.util.List;

public class WordData {

    public static List<String> wordList = new ArrayList<>();

    public static List<String> deletingWordList = new ArrayList<>();

    public static List<RainingWord> deletingRainingWordList = new ArrayList<>();

    public static int pastDeleteWordListSize = 0;


    // 서버 -> 클라이언트 로 온 word를 정제한다.
    public static void refineRawString(Chat.Word word) {


        if (word.getType().equals("Create"))
            wordList.add(word.getWord());
        else if (word.getType().equals("Delete")) {

            // 기존에 없는 것만 추가하는 방식을 취해보자
            boolean hasString = false;
            for (String ds : deletingWordList) {
                if (ds.equals(word.getWord()))
                    hasString = true;
            }
            if (!hasString)
                deletingWordList.add(word.getWord());
        }
    }



    //deletingWordList 의 내용을 deletingRainingWordList에 추가한다.
    public static void updateDelRainingWorListByServer() {
        for (View view : DataHelper.getViews()) {
            if (view instanceof RainingWord) {
                RainingWord rainingWord = (RainingWord) view;


                for (String s : deletingWordList) { // 텅 비어 있는 이유가 뭐지?
//                    System.out.println("hi1");
                    if (rainingWord.word.equals(s)) {
                        deletingRainingWordList.add(rainingWord);
//                        System.out.println("hi2");
                    }

                }
            }
        }

    }




    public static String getSendingDeleteStrings() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Delete:");
        stringBuilder.append(deletingWordList.get(deletingWordList.size() - 1));
        stringBuilder.append(",");

        if (stringBuilder.toString().length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        System.out.println("send : " + stringBuilder.toString());
        return stringBuilder.toString();

    }

    public static Chat.Word getSendingDeleteWord() {

        Chat.Word word = Chat.Word.newBuilder()
                                    .setType("Delete")
                                    .setWord(deletingWordList.get(deletingWordList.size() - 1))
                                    .build();

        return word;
    }

}
