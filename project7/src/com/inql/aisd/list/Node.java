package com.inql.aisd.list;

public class Node {

    private int key;
    private Node next;

    public Node(){}

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node " +
                "key='" + key + '\'';
    }
}

