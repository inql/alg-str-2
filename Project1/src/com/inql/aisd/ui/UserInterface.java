package com.inql.aisd.ui;

import com.inql.aisd.Utilities;
import com.inql.aisd.redblack.RedBlackTree;

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
}
