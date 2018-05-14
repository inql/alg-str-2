package com.inql.aisd.disjointset;

public class Node {

    private int key;
    private int rank;
    private Node parent;

    public Node(int key) {
        this.key = key;
        this.rank = 0;
        this.parent = this;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void incrementRank(){
        this.rank++;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", rank=" + rank +
                ", parent=" + parent.getKey() +
                '}';
    }
}
