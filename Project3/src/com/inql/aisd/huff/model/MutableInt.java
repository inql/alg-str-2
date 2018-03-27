package com.inql.aisd.huff.model;

public class MutableInt {

    private int value = 1;

    public void increment(){
        ++value;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
