package com.inql.aisd;

import com.inql.aisd.disjointset.DisjointSetsUtil;
import com.inql.aisd.disjointset.Node;
import com.inql.aisd.graph.Graph;
import com.inql.aisd.kruskal.Kruskal;

public class Main {

    public static void main(String[] args) {
//        disjoinSetsTest();
        kruskalTest();
    }

    public static void disjoinSetsTest(){
        Node[] nodes = new Node[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = DisjointSetsUtil.makeSet(i+1);
        }
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[0]),DisjointSetsUtil.findSet(nodes[1]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[2]),DisjointSetsUtil.findSet(nodes[3]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[4]),DisjointSetsUtil.findSet(nodes[3]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[0]),DisjointSetsUtil.findSet(nodes[4]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[5]),DisjointSetsUtil.findSet(nodes[6]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[7]),DisjointSetsUtil.findSet(nodes[8]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[5]),DisjointSetsUtil.findSet(nodes[7]));
        DisjointSetsUtil.union(DisjointSetsUtil.findSet(nodes[6]),DisjointSetsUtil.findSet(nodes[3]));

        for (Node node :
                nodes) {
            System.out.println(node);
        }
    }

    public static void kruskalTest(){
        Graph graph = new Graph();
        Kruskal kruskal = new Kruskal();
        for(int i =0; i<5; i++)
        {
            graph.addVerticle(i);
        }
        graph.addEdge(0,1,1);
        graph.addEdge(0,4,2);
        graph.addEdge(1,2,5);
        graph.addEdge(1,3,2);
        graph.addEdge(1,4,1);
        graph.addEdge(2,3,7);
        graph.addEdge(3,4,3);
        kruskal.kruskal(graph);
        System.out.println(kruskal.kruskalList);
    }
}
