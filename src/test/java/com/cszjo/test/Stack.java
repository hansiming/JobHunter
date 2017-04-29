package com.cszjo.test;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by Han on 2017/4/4.
 */
public class Stack {

    private Object[] elements;
    private int size = 0;
    private int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if(size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    private void ensureCapacity() {
        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
