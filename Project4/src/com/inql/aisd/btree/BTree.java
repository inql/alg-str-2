package com.inql.aisd.btree;

import java.util.Arrays;

public class BTree {

    private class Node{
        int order;
        int key[];
        int counter;
        Node parent;
        Node[] child;
        boolean isLeaf;

        Node(Node parent, int order){
            this.order = order;
            key = new int[2*order-1];
            counter = 0;
            this.parent = parent;
            child = new Node[2*order];
            isLeaf = true;
        }

        void setKeyValue(int index, int value){key[index] = value;}

        void setChildValue(int index, Node value){child[index] = value;}

        int getKeyIndex(int value){
            return Arrays.asList(key).indexOf(value);
        }

        int getKeyValue(int index){
            return key[index];
        }

        Node getChild(int index){
            return child[index];
        }
    }

    private int degree;
    Node root;

    BTree(int degree){
        root = new Node(null, degree);
        this.degree = degree;
    }

    private Node search(Node x, int k){
        int i =0;
        while(i < x.counter && k > x.key[i])
            i++;
        if(i<x.counter && k == x.key[i])
            return x;
        if(x.isLeaf){
            return null;
        }else{
            return  search(x.getChild(i),k);
        }
    }

    public void search(BTree bTree, int value){
        Node temp = search(bTree.root,value);
        if(temp == null){
            System.out.println("Nie znaleziono");
        }else{
            System.out.println("Znaleziono");
        }
    }

    private void splitChild(Node x, int i, Node y){
        Node z = new Node(null, degree);
        z.isLeaf = y.isLeaf;
        z.counter = degree -1;
        System.arraycopy(y.key, degree, z.key, 0, degree - 1);
        if(!y.isLeaf){
            System.arraycopy(y.child, degree, z.child, 0, degree);
        }
        y.counter = degree -1;
//        System.arraycopy(x.child, i + 1, x.child, i + 1 + 1, x.counter - i);
        for (int j = x.counter; j > i; j--){
            x.child[j+1] = x.child[j];
        }
        x.child[i+1]=z;
//        System.arraycopy(x.key, i, x.key, i + 1, x.counter - 1 - i - 1);
        for(int j = x.counter -1; j> i-1; j--){
            x.key[j+1]=x.key[j];
        }
        x.key[i]=y.key[degree-1];
        x.counter++;
        y.key[degree-1]=0;
        for(int j=0; j<degree-1; j++){
            y.key[j+degree]=0;
        }
    }

    void insert(BTree bTree, int k){
        Node r = bTree.root;
        if(r.counter == (2*degree -1)){
            Node s = new Node(null, degree);
            bTree.root = s;
            s.isLeaf = false;
            s.counter = 0;
            s.child[0] = r;
            splitChild(s,0,r);
            insertNonFull(s,k);
        }
        else{
            insertNonFull(r,k);
        }
    }

    private void insertNonFull(Node x, int k){
        int i = x.counter;
        if(x.isLeaf){
            while (i>=1 && k< x.key[i-1]){
                x.key[i] = x.key[i-1];
                i--;
            }
            x.key[i] = k;
            x.counter++;
        }
        else{
            int j = 0;
            while(j<x.counter && k > x.key[j]) j++;
            if(x.child[j].counter == (2*degree-1)){
                splitChild(x,j,x.child[j]);
                if(k>x.key[j]) j++;
            }
            insertNonFull(x.child[j],k);
        }
    }

    public void delete(Node x, int k){
        if(x==search(x,k)){
            if(x.isLeaf){

            }
        }
    }

    public void print(Node n){
        for(int i =0; i<n.counter; i++){
            if (i == 0) {
                System.out.print("( ");
            }
            System.out.print(n.getKeyValue(i));
            if(i < n.counter - 1){
                System.out.print(" | ");
            }
            if(i==n.counter -1){
                System.out.print(" )");
            }
        }
        if(!n.isLeaf){
            System.out.print("\n[");
            for(int j=0; j<=n.counter; j++){
                if(n.getChild(j) != null){
                    print(n.getChild(j));
                }
                if(j<n.counter){
                    System.out.print(" | ");
                }
            }
            System.out.print("]\n");
        }
    }








}
