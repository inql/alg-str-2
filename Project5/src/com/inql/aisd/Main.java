package com.inql.aisd;

import com.inql.aisd.disjointset.DisjointSetsUtil;
import com.inql.aisd.disjointset.Node;

public class Main {

    public static void main(String[] args) {
        disjoinSetsTest();
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
}
