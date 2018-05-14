package com.inql.aisd.graph;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Integer> vertices;
    private ArrayList<Edge> edgeArrayList;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edgeArrayList = new ArrayList<>();
    }

    public void addVerticle(int value){
        vertices.add(value);
    }

    public void addEdge(int src, int dest, int weight){
        edgeArrayList.add(new Edge(src,dest,weight));
    }

    public ArrayList<Integer> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }
}
