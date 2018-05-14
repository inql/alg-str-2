package com.inql.aisd.graph;

public class Edge implements Comparable<Edge>{

    private int src;
    private int dest;
    private int weight;

    public Edge(int src, int dest, int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int getSrc(){
        return src;
    }

    public int getDest(){
        return dest;
    }

    public int getWeight(){
        return weight;
    }

    public String toString(){
        return this.src + " - " + this.dest + " => weight: " + this.weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return Integer.compare(this.getWeight(), edge.getWeight());
    }
}
