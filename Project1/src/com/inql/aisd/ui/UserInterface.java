package com.inql.aisd.ui;

import com.inql.aisd.Utilities;
import com.inql.aisd.redblack.RedBlackTree;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final RedBlackTree redBlackTree = new RedBlackTree();

    public void run(){
        String command ="";
        while(!command.toLowerCase().equals("q")){
            System.out.println(Utilities.repeat("-",40));
            System.out.println("\nChoose an option:\n" +
                    "\t1. - Add item\n" +
                    "\t2. - Delete item\n" +
                    "\t3. - Find item\n" +
                    "\t4. - Print tree\n" +
                    "\t5. - Delete tree\n" +
                    "\t6. - I don't care - just show me test\n" +
                    "\tQ. - Quit\n");
            command = scanner.next();
            switch (command.toLowerCase()){
                case "1":
                    try{
                        redBlackTree.redBlackInsert(getInputFromUser());
                    }catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                    break;
                case "2":
                    try{
                        redBlackTree.redBlackDelete(getInputFromUser());
                    }catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                    break;
                case "3":
                    try{
                        if(redBlackTree.getSentinel() == redBlackTree.treeSearch(getInputFromUser()))
                            System.out.println("Item not found");
                        else
                            System.out.println("Item found");
                        redBlackTree.treeSearch(getInputFromUser());
                    }catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                    break;
                case "4":
                    System.out.println("Print:\n\t1. - preorder\n\t2. - inorder\n\t3. - postorder\n\t4. - the prettiest\n");
                    switch (scanner.next()){
                        case "1":
                            redBlackTree.preorderPrint();
                            break;
                        case "2":
                            redBlackTree.inorderPrint();
                            break;
                        case "3":
                            redBlackTree.postorderPrint();
                            break;
                        case "4":
                            System.out.println(redBlackTree);
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    break;
                case "5":
                    redBlackTree.treeDelete();
                    System.out.println("Tree has been succesfully deleted");
                    break;
                case "6":
                    doTest();
                    break;
                case "q":
                    System.out.println("Thanks for using!");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private Integer getInputFromUser(){
        System.out.print("Input the value u want to add/find/delete: ");
        return scanner.nextInt();
    }

    private void doTest(){
        RedBlackTree redBlackTree = new RedBlackTree();
        int[] numbers = {3, 25, 14, 29, 10, 17, 23, 2, 11, 1 ,15, 16, 21, 6, 8, 20, 27, 24, 4, 5, 22, 7, 13, 9,9,9};
        ArrayList<Integer> toInsert = new ArrayList<>();
        for (int value:
             numbers) {
            toInsert.add(value);
        }
        redBlackTree.massInsert(toInsert);
        System.out.println(Utilities.repeat("-",40)+"\nTree after inserting:\n");
        System.out.println(redBlackTree);
        System.out.println(Utilities.repeat("-",40)+"\nInorder:\n");
        redBlackTree.inorderPrint();
        System.out.println(Utilities.repeat("-",40)+"\nFinding values:\n");
        System.out.println(redBlackTree.treeSearch(1));
        System.out.println(redBlackTree.treeSearch(23));
        System.out.println(redBlackTree.treeSearch(99));
        System.out.println(Utilities.repeat("-",40)+"\nTime to delete some!\n");
        System.out.println("Deleting :"+redBlackTree.redBlackDelete(14));
        System.out.println("After:\n"+redBlackTree);
        System.out.println("Deleting :"+redBlackTree.redBlackDelete(27));
        System.out.println("After:\n"+redBlackTree);
        System.out.println("Deleting :"+redBlackTree.redBlackDelete(4));
        System.out.println("After:\n"+redBlackTree);
        System.out.println("Deleting :"+redBlackTree.redBlackDelete(9));
        System.out.println("After (but only counter was decreased):\n"+redBlackTree);
        System.out.println("Deleting :"+redBlackTree.redBlackDelete(98));
        System.out.println("After (but not found):\n"+redBlackTree);
    }
}

