package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class WordData {

    public static List<String> wordList = new ArrayList<>();

    public static List<String> deletingWordList = new ArrayList<>();

    public static List<RainingWord> deletingRainingWordList = new ArrayList<>();

    public static int pastDeleteWordListSize = 0;


    public static void refineRawString(String rawString) {

        String[] strings = rawString.split(",");

        for (String s : strings) {
            String[] protocolString;
            protocolString = s.split(":");
            if (protocolString[0].equals("Create"))
                wordList.add(protocolString[1]);
            else if (protocolString[0].equals("Delete")&&
                    protocolString.length>1) {

                // 기존에 없는 것만 추가하는 방식을 취해보자
                boolean hasString = false;
                for (String ds : deletingWordList) {
                    if (ds.equals(protocolString[1]))
                        hasString = true;
                }
                if (!hasString)
                    deletingWordList.add(protocolString[1]);
            }
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


/*
    public static String getSendingDeleteStrings() {

        StringBuilder stringBuilder = new StringBuilder();

        for (RainingWord rainingWord : deletingRainingWordList) {
            stringBuilder.append("Delete:");
            stringBuilder.append(rainingWord.word);
            stringBuilder.append(",");
        }
        if (stringBuilder.toString().length() > 0)
            stringBuilder.delete(stringBuilder.toString().length() - 1, stringBuilder.toString().length());

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
*/


    public static String getSendingDeleteStrings() {

        StringBuilder stringBuilder = new StringBuilder();

//        for (String s: deletingWordList) {
            stringBuilder.append("Delete:");
            stringBuilder.append(deletingWordList.get(deletingWordList.size()-1));
            stringBuilder.append(",");
//        }
        if (stringBuilder.toString().length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length()-1);

        System.out.println("send : " + stringBuilder.toString());
        return stringBuilder.toString();

    }

}
