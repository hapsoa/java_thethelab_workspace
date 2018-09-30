package io.thethelab;

class Printer {
    private String printerName;

    public void setName(String name) {
        this.printerName = name;
    }

    public void print() {}
}

class User {
    String name;
    int age;

    void foo() {
        Printer printer = new Printer();
        // printer.name = "Hello";
        printer.setName("Hello");
    }
}



public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
