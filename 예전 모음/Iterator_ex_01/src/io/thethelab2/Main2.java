package io.thethelab2;

// 순회(Traverse)
// => Stack을 다른 사람들이 내부 구조를 알지 못한다고 하더라도
// 순회를 하게 하고 싶다.

// 컬렉션(Collection)의 내부 구조에 상관없이 요소를 순회(열거)하고 싶다
// => Iterator

// 나의 컬렉션이 Iterator를 제공하고 있다.
// => Iterable

// 반복자(Iterator)를 사용할 때 주의할점
// => 순회 동안 절대 내부 요소에 크기의 변경이 발생하면 안된다.
// : Concurrent Modification Exception

import java.util.Iterator;

class CountDownIterator<E> implements Iterator<E> {
    private int n;

    public CountDownIterator(int n) {
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        if (n < 0)
            return false;
        else
            return true;
    }

    @Override
    public E next() {
        return (E) n--;
    }
}

class CountDown implements Iterable{
    private int n;
    public CountDown(int n) {
        this.n = n;
    }

    @Override
    public Iterator iterator() {
        return new CountDownIterator(n);
    }
}


public class Main2 {
    public static void main(String[] args) {
        CountDown c = new CountDown(10);

        for (Object e : c) {
            System.out.println(e);
        }

// 10
// 9
// 8
// 7
// 6
// ...
// 1
// 0
    }
}