package com.inql.aisd;

import com.inql.aisd.list.Node;

import java.util.LinkedList;

public class Dfs {

    private Node[] adjacencyList;
    private boolean[] isVisited;
    LinkedList<String> paths;

    public Dfs(Node[] adjacencyList){
        this.adjacencyList = adjacencyList;
        this.isVisited = new boolean[this.adjacencyList.length];
        this.paths = new LinkedList<>();
    }

    public void dfsCalculate(){
        dfsCalculate(0);
        System.out.println(paths);
    }

    public void dfsCalculate(int v){
        int i;
        isVisited[v] = true;
        System.out.println("Teraz przechodzę przez: "+v);
        Node current = adjacencyList[v];
        while(current!=null){
            if(!isVisited[current.getKey()]){
                paths.add("Krawędź: "+v+" --> "+current.getKey());
                dfsCalculate(current.getKey());
            }
            current = current.getNext();
        }
    }
}
