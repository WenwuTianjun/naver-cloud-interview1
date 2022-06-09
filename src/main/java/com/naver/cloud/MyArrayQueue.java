package com.naver.cloud;

/**
 * Implement a Queue of your own design with an array or stack respectively, including push, pop, peek, empty methods.
 */
public class MyArrayQueue<E> {
    private int head;
    private int tail;
    private final int maxSize;
    private final Object[] arr;

    public MyArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        head = 0;
        tail = 0;
    }

    /**
     * 判断数组是否以满
     *
     * @return
     */
    public Boolean isFull() {
        return (tail + 1) % maxSize == head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 添加数据，并移动尾部指针
     *
     * @param e 添加元素
     * @return 是否成功
     */
    public boolean push(E e) {
        if (isFull()) {
            return false;
        }
        arr[tail] = e;

        tail = (tail + 1) % maxSize;
        return tail < maxSize;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) arr[0];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }

        Object o = arr[0];
        for (int i = 0; i < tail - 1; i++) {
            arr[i] = arr[i + 1];
            if (i == tail - 2) {
                arr[i] = null;
            }
        }
        tail = tail - 1;
        return (E) o;
    }

    /**
     * 清空队列
     */
    public void empty() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < tail; i++) {
            arr[i] = null;
        }
        this.head = 0;
        this.tail = 0;
    }
}
