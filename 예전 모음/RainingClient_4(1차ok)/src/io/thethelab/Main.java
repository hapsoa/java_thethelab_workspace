package io.thethelab;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WordReceivingClient wordReceivingClient = new WordReceivingClient();
        Window.main("io.thethelab.Window");
        wordReceivingClient.start();

    }
}
