package com.inql.aisd.kruskal;

import com.inql.aisd.disjointset.DisjointSetsUtil;
import com.inql.aisd.disjointset.Node;
import com.inql.aisd.graph.Edge;
import com.inql.aisd.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public List<Edge> kruskalList;

    public void kruskal(Graph graph){
        kruskalList = new ArrayList<>();
        Node[] vertices = new Node[graph.getVertices().size()+1];
        for (int i=1; i<graph.getVertices().size()+1;i++){
            vertices[graph.getVertices().get(i-1)]=new Node(graph.getVertices().get(i-1));
        }
        Collections.sort(graph.getEdgeArrayList());
        for(Edge edge: graph.getEdgeArrayList()){
            if(DisjointSetsUtil.findSet(vertices[edge.getSrc()]) !=
                    DisjointSetsUtil.findSet(vertices[edge.getDest()])){
                kruskalList.add(edge);
                DisjointSetsUtil.union(vertices[edge.getSrc()],vertices[edge.getDest()]);
            }
        }
    }
}
