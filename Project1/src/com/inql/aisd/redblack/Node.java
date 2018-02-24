package com.inql.aisd.redblack;

public class Node implements Comparable<Node>, Cloneable{

    private Integer value;
    private Node leftSon;
    private Node rightSon;
    private Node parent;
    private Color color;
    private int counter;

    public Node(){}

    public Node(int value){
        this.value = value;
        this.leftSon = this.rightSon = this.parent = null;
        this.counter = 1;
        this.color = Color.RED;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public Node getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increaseCounter(){
        this.counter++;
    }

    public void decreaseCounter(){
        this.counter--;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node clone(){
        Node result;
        try{
            result = (Node) super.clone();
            result.setValue(value);
            return result;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int compareTo(Node o) {
        if(o!=null && o.getValue()!=null) return this.getValue() - o.getValue();
        return -this.getValue();
    }

    @Override
    public String toString() {
        return
                "v:" + value +
                "c:" + counter +
                ":"  + color;
    }
}
