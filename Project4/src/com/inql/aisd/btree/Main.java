package com.inql.aisd.btree;

public class Main {

    public static void main(String[] args) {
        BTree bTree = new BTree(3);
        for (int i =1; i<1000; i++){
            bTree.insert(bTree,i);
        }
        bTree.print(bTree.root);
    }
}
