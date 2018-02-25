package com.inql.aisd;

import com.inql.aisd.redblack.RedBlackTree;
import com.inql.aisd.ui.UserInterface;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.run();

    }

    public static void test(){
        RedBlackTree redBlackTree = new RedBlackTree();
        ArrayList<Integer> toInsert = new ArrayList<Integer>(){{add(7);
            add(11);
            add(9);
            add(18);
            add(14);
            add(12);
            add(17);
            add(19);
            add(22);
            add(20);
            add(1);
            add(2);
            add(3);
            add(23);
            add(13);}};
        redBlackTree.massInsert(toInsert);
        System.out.println(redBlackTree);
        redBlackTree.redBlackDelete(19);
        System.out.println(redBlackTree);
        redBlackTree.redBlackDelete(1);
        System.out.println(redBlackTree);
        redBlackTree.redBlackDelete(20);
        System.out.println(redBlackTree);
    }
}
