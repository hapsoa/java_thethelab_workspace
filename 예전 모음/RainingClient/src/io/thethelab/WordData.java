package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class WordData {

    public static List<String> wordList = new ArrayList<>();

    public static List<String> deletingWordList = new ArrayList<>();


    public static List<RainingWord> deletingRainingWordList = new ArrayList<>();


    public static void refineRawString(String rawString) {

        String[] strings = rawString.split(",");

        for (String s : strings) {
            String[] protocolString;
            protocolString = s.split(":");
            if (protocolString[0].equals("Create"))
                wordList.add(protocolString[1]);
            else if (protocolString[0].equals("Delete")&&
                    protocolString.length>1) {
                deletingWordList.add(protocolString[1]);
            }

        }

    }

    //deletingWordList 의 내용을 deletingRainingWordList에 추가한다.
    public static void updateDelRainingWorListByServer() {
        for (View view : DataHelper.getViews()) {
            if (view instanceof RainingWord) {
                RainingWord rainingWord = (RainingWord) view;

                for (String s : deletingWordList) {

                    if (rainingWord.word.equals(s)) {
                        deletingRainingWordList.add(rainingWord);
                    }

                }
            }
        }
    }



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

}
