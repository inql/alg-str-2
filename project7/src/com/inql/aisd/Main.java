package com.inql.aisd;

import com.inql.aisd.list.Node;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            int[][] result = DataInit.initializeDataArray(args[0]);
            System.out.println("Macierz sąsiedztw: ");
            for(int i =0; i<result.length; i++){
                for(int j=0; j<result[i].length; j++){
                    System.out.print(result[i][j]+" ");
                }
                System.out.println();
            }
            //tworzymy i umieszczamy do listy sąsiedztw
            Node[] adjacencyList = new Node[result.length];
            for(int i =0; i<adjacencyList.length; i++){
                for (int j =0; j<result[i].length; j++){
                    if(result[i][j]==1){
                        if(adjacencyList[i]==null){
                            adjacencyList[i] = new Node(j);
                        }else{
                            Node current = adjacencyList[i].getNext();
                            Node parent = adjacencyList[i];
                            while(current!=null){
                                current = current.getNext();
                                parent = parent.getNext();
                            }
                            parent.setNext(new Node(j));
                        }
                    }
                }
                Node current = adjacencyList[i];
                System.out.println("i - "+i);
                while(current!=null){
                    System.out.println(current);
                    current = current.getNext();
                }
                System.out.println("---------------------------------");
            }
        Dfs dfs = new Dfs(adjacencyList);
            dfs.dfsCalculate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
