package io.thethelab;

public class Program {

    public static void main(String[] args) {

        Thread thread = new Thread(new Acceptor(5000));
        thread.start();
    }
}
