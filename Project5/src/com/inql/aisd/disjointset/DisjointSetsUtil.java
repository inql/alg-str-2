package com.inql.aisd.disjointset;

public class DisjointSetsUtil {

    public static Node makeSet (int value){
        return new Node(value);
    }

    public static Node findSet (Node x){
        if(x != x.getParent()){
            x.setParent(findSet(x.getParent()));
        }
        return x.getParent();
    }

    public static void union(Node x, Node y){
        if(x.getRank() > y.getRank())
            y.setParent(x);
        else {
            x.setParent(y);
            if(x.getRank()==y.getRank())
                y.incrementRank();
        }
    }

}
