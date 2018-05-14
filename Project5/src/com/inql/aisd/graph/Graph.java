package com.inql.aisd.graph;

import com.inql.aisd.disjointset.DisjointSetsUtil;
import com.inql.aisd.disjointset.Node;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodeArrayList;
    private int currentIntValue;

    public Graph() {
        this.nodeArrayList = new ArrayList<>();
        this.currentIntValue = 1;
    }

    public void addVerticle(){
        nodeArrayList.add(DisjointSetsUtil.makeSet(currentIntValue++));
    }

    public void addEdge(Node x, Node y){
        DisjointSetsUtil.union(x,y);
    }


}
