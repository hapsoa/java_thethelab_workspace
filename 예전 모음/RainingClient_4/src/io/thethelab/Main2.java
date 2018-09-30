package io.thethelab;

public class Main2 {

    public static void main(String[] args) {
        // write your code here
        WordReceivingClient wordReceivingClient = new WordReceivingClient();
        Window.main("io.thethelab.Window");
        wordReceivingClient.start();

    }

}
