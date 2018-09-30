package server;

public class Main {

    public static void main(String[] args) {
	
        Acceptor acceptor = new Acceptor(5000);
        acceptor.start();
    }
}

