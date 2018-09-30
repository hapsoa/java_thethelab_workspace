package io.thethelab;

import processing.data.JSONObject;

import java.util.Arrays;

public class example3 {
    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.setString("type", "connect");

        JSONObject stateJsonObject = new JSONObject();
        stateJsonObject.setString("name", "jaejong");
        stateJsonObject.setInt("character", 10);

        JSONObject positionJsonObject = new JSONObject();
        positionJsonObject.setFloat("x", 10.0f);
        positionJsonObject.setFloat("y", 20.0f);

        stateJsonObject.setJSONObject("position", positionJsonObject);

        jsonObject.setJSONObject("state", stateJsonObject);

        System.out.println(jsonObject.toString());

        System.out.println(jsonObject.getString("type"));


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();








    }





    // 아래의 방법 외에 다음과 같이 간단한 방법도 존재한다.  :
//   byte[] byteArray = ByteBuffer.allocate(4).putInt(value).array();
    public static byte[] intToByte2Array(int value) {
        byte[] byteArray = new byte[2];
        byteArray[0] = (byte)(value >> 8);
        byteArray[1] = (byte)(value);
        return byteArray;
    }

    public static int byte2ArrayToInt(byte bytes[]) {
        return ((((int)bytes[0] & 0xff) << 8) |
                (((int)bytes[1] & 0xff)));
    }


}
