package io.thethelab;

public class ProducerConsumerPattern {

    public static void main(String[] args) {
        Table table= new Table(100);

        new producerThread(table).start();
        new consumerThread(table).start();
    }

}

class producerThread extends Thread {

    private static int id = 0;

    Table table;

    public producerThread(Table table) {
        this.table = table;
    }

    public void run() {

        while(true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String packet = "No : " + nextId();

            try {
                table.put(packet); // 큐에 추가
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private static synchronized int nextId() {

        return id++;

    }

}


class consumerThread extends Thread {

    private final Table table;

    public consumerThread(Table table) {
        this.table = table;
    }

    public void run() {

        while (true) {
            String packet = null;
            try {
                packet = table.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("consumer : " + packet);
        }

    }

}


class Table{
    private final String[] buffer;

    private int tail;
    private int head;

    private int count;

    public Table(int count) {

        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;

    }

    public synchronized void put(String packet) throws InterruptedException {

        while(count >= buffer.length) {
            wait();
        }


        buffer[tail] = packet;  // 후입하라!
        tail =(tail+1) % buffer.length; // Circular 큐라서 tail의 위치가 바뀜
        count++;
        notifyAll(); // 버퍼가 먼가가 들어 갔으니 take해도 된다고 이벤트 날림!!

    }

    public synchronized String take() throws InterruptedException {

        while(count <= 0) { // 버퍼에 아무것도 없으면 대기!
            wait();
        }

        String packet = buffer[head]; // 선출하라!

        head = (head + 1) % buffer.length; // Circular 큐라서 header의 위치가 바뀜!
        count--;

        notifyAll(); // 버퍼에서 하나를 가져갔으니 put 해도 된다고 이벤트 날림!!!

        return packet;

    }






}





















