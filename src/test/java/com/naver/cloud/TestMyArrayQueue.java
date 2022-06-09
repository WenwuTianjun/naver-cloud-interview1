package com.naver.cloud;

import org.junit.Test;

public class TestMyArrayQueue {

    @Test
    public void testPush() {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>(16);
        queue.push(12);
        System.out.println(queue.peek());
    }

    @Test
    public void testPop() {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>(16);
        queue.push(12);
        queue.push(13);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
    }

    @Test
    public void testEmpty() {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>(16);
        queue.push(12);
        queue.push(13);
        queue.push(14);
        queue.empty();
        System.out.println(queue.peek());
    }
}
