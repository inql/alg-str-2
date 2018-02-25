package com.inql.aisd.redblack;

import com.inql.aisd.Utilities;

import java.util.ArrayList;
import static com.inql.aisd.redblack.Color.*;

public class RedBlackTree {

    private class Node implements Comparable<Node>{
        private Integer value = null;
        private Node leftSon = sentinel;
        private Node rightSon = sentinel;
        private Node parent = sentinel;
        private Color color = RED;
        private int counter = 1;

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getLeftSon() {
            return leftSon;
        }

        public void setLeftSon(Node leftSon) {
            this.leftSon = leftSon;
        }

        public Node getRightSon() {
            return rightSon;
        }

        public void setRightSon(Node rightSon) {
            this.rightSon = rightSon;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        public void increaseCounter(){
            this.counter++;
        }

        public void decreaseCounter(){
            this.counter--;
        }

        @Override
        public int compareTo(Node o) {
            if(o!=null && o.getValue()!=null) return this.getValue() - o.getValue();
            return -this.getValue();
        }

        @Override
        public String toString() {
            return
                    "v:" + value +
                            "c:" + counter +
                            ":"  + color;
        }
    }

    private final Node sentinel = new Node(null);
    private Node root = sentinel;

    public RedBlackTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void leftRotate(Node toRotate){
        Node rightSon = toRotate.getRightSon();
        toRotate.setRightSon(rightSon.getLeftSon());
        if(rightSon.getLeftSon()!=sentinel)
            rightSon.getLeftSon().setParent(toRotate);
        rightSon.setParent(toRotate.getParent());
        if(toRotate.getParent() == sentinel)
            root = rightSon;
        else if (toRotate == toRotate.getParent().getLeftSon())
            toRotate.getParent().setLeftSon(rightSon);
        else toRotate.getParent().setRightSon(rightSon);
        rightSon.setLeftSon(toRotate);
        toRotate.setParent(rightSon);
    }

    public void rightRotate(Node toRotate){
        Node leftSon = toRotate.getLeftSon();
        toRotate.setLeftSon(leftSon.getRightSon());
        if(leftSon.getRightSon()!=sentinel)
            leftSon.getRightSon().setParent(toRotate);
        leftSon.setParent(toRotate.getParent());
        if(toRotate.getParent()==sentinel)
            root = leftSon;
        else if(toRotate == toRotate.getParent().getRightSon())
            toRotate.getParent().setRightSon(leftSon);
        else
            toRotate.getParent().setLeftSon(leftSon);
        leftSon.setRightSon(toRotate);
        toRotate.setParent(leftSon);
    }
    /**
     * Metoda wypisuje zawartość drzewa w postaci postorder
     */

    public void postorderPrint() {
        postorderPrint(root, maxDepth());
    }

    private void postorderPrint(Node x, int counter) {
        if (x == sentinel)
            return;
        postorderPrint(x.getLeftSon(), --counter);
        counter++;
        postorderPrint(x.getRightSon(), --counter);
        System.out.println(Utilities.repeat(" ",counter)+x);

    }
    public void inorderPrint() {
        inorderPrint(root, maxDepth());
    }

    private void inorderPrint(Node x, int counter) {
        if (x == sentinel)
            return;
        inorderPrint(x.getLeftSon(), --counter);
        System.out.println(Utilities.repeat(" ",counter)+x);
        counter++;
        inorderPrint(x.getRightSon(), --counter);

    }
    /**
     * Metoda <code>insertNode</code> umieszcza węzeł w argumencie jawnym do drzewa, zachowując zasady drzew binarnych
     * @param node - węzeł do umieszczenia
     *
     */

    public void redBlackInsert(Node node){
        insertNode(node);
        Node uncle = sentinel;
        while(node != root && node.getParent().getColor()== RED){
            if(node.getParent() == node.getParent().getParent().getLeftSon()){
                uncle = node.getParent().getParent().getRightSon();
                if(uncle != sentinel && uncle.getColor()== RED){
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                }
                else {
                    if (node == node.getParent().getRightSon()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    rightRotate(node.getParent().getParent());
                }
            }
            else{
                uncle = node.getParent().getParent().getLeftSon();
                if(uncle != sentinel && uncle.getColor() == RED){
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                }
                else {
                    if (node == node.getParent().getLeftSon()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    leftRotate(node.getParent().getParent());
                }
            }
        }
        root.setColor(BLACK);
    }

    private void insertNode(Node node) {
        Node current = root;
        Node nodesFather = sentinel;
        while (current != sentinel) {
            nodesFather = current;
            if(node.compareTo(current)==0) {
                node.increaseCounter();
            }
            else if (node.compareTo(current)<0) current = current.getLeftSon();
            else if (node.compareTo(current)>0) current = current.getRightSon();
        }
        node.setParent(nodesFather);
        if (nodesFather == sentinel)
            root = node;
//        else if (node.compareTo(nodesFather)==0) nodesFather.setLeftSon(node);
        else if (node.compareTo(nodesFather)<0)
            nodesFather.setLeftSon(node);
        else
            nodesFather.setRightSon(node);
        node.setLeftSon(sentinel);
        node.setRightSon(sentinel);
    }
    /**
     *
     * @param values - tablica węzłów do dodania
     */

    public void massInsert(ArrayList<Integer> values){
        for(int value : values){
            redBlackInsert(new Node(value));
        }
    }

    public Node redBlackDelete(int value){
        Node toDelete = treeSearch(value,root);
        if(toDelete!=sentinel){
            if(toDelete.getCounter()==1){
                return redBlackDelete(toDelete);
            }
                toDelete.decreaseCounter();
                return toDelete;
        }
        return sentinel;
    }

    private Node redBlackDelete(Node toDelete){
        Node x,y;
        if(toDelete.getLeftSon() == sentinel || toDelete.getRightSon() == sentinel)
            y = toDelete;
        else
            y = treeSuccessor(toDelete);
        if(y.getLeftSon() != sentinel)
            x = y.getLeftSon();
        else{
            x = y.getRightSon();
        }

        x.setParent(y.getParent());
        if(y.getParent() == sentinel)
            this.root = x;
        else {
            if (y == y.getParent().getLeftSon())
                y.getParent().setLeftSon(x);
            else
                y.getParent().setRightSon(x);
        }
        if(y!=toDelete){
            toDelete.setValue(y.getValue());
            toDelete.setCounter(y.getCounter());
        }
        if(y.getColor()==BLACK)
            redBlackDeleteFixUp(x);
        return y;
    }

    private void redBlackDeleteFixUp(Node toFix){
        Node w;
        while(toFix != root && toFix.getColor()==BLACK){
            if(toFix == toFix.getParent().getLeftSon()){
                w = toFix.getParent().getRightSon();
                if(w.getColor()==RED){
                    w.setColor(BLACK);
                    toFix.getParent().setColor(RED);
                    leftRotate(toFix.getParent());
                    w = toFix.getParent().getRightSon();
                }
                if(w.getLeftSon().getColor() == BLACK && w.getRightSon().getColor() == BLACK){
                    w.setColor(RED);
                    toFix = toFix.getParent();
                }
                else
                    if (w.getRightSon().getColor() == BLACK) {
                        w.setColor(RED);
                        rightRotate(w);
                        w = toFix.getParent().getRightSon();
                    }
                    w.setColor(toFix.getParent().getColor());
                    toFix.getParent().setColor(BLACK);
                    w.getRightSon().setColor(BLACK);
                    leftRotate(toFix.getParent());
                    toFix = root;
            }
            else{
                w = toFix.getParent().getLeftSon();
                if(w.getColor()==RED){
                    w.setColor(BLACK);
                    toFix.getParent().setColor(RED);
                    rightRotate(toFix.getParent());
                    w = toFix.getParent().getLeftSon();
                }
                if(w.getRightSon().getColor() == BLACK && w.getLeftSon().getColor() == BLACK){
                    w.setColor(RED);
                    toFix = toFix.getParent();
                }
                else
                    if (w.getLeftSon().getColor() == BLACK) {
                        w.setColor(RED);
                        leftRotate(w);
                        w = toFix.getParent().getLeftSon();
                    }
                    w.setColor(toFix.getParent().getColor());
                    toFix.getParent().setColor(BLACK);
                    w.getLeftSon().setColor(BLACK);
                    rightRotate(toFix.getParent());
                    toFix = root;

            }
        }
        toFix.setColor(BLACK);
    }
    /**
     * Metoda <code>treeSearch</code> wyszukuje podaną wartość w drzewie
     * @param value - wartość do wyszukania
     * @param root - korzeń poddrzewa od którego zaczynamy szukanie
     * @return - zwraca referencję do obiektu Node lub null jeśli wartość nie znajduje się w drzewie
     */

    public Node treeSearch(int value, Node root){
        Node current = root;
        while(current != sentinel && current.getValue()!=value){
            if(value <  current.getValue())
                current = current.getLeftSon();
            else
                current = current.getRightSon();
        }
        return current;
    }
    /**
     * Zwraca głębokość drzewa
     * @return - głębokość drzewa
     */

    public int maxDepth(){
        return maxDepth(root);
    }

    public int maxDepth(Node node) {
        if (node == sentinel) return 0;
        return 1 + Math.max(maxDepth(node.getLeftSon()), maxDepth(node.getRightSon()));
    }
    /**
     * Metoda <code>treeMinimum</code> zwraca skrajny lewy węzeł
     * w poddrzewie o korzeniu x, czyli węzeł o najmniejszym kluczu w tym poddrzewie
     * @param x - korzeń poddrzewa
     * @return zwraca najmniejszą wartość
     */

    public Node treeMinimum(Node x){
        while(x.getLeftSon()!=sentinel){
            x = x.getLeftSon();
        }
        return x;
    }

    /**
     * Wyznacza następnik danego węzła
     * @param node - dany węzeł
     * @return następnik
     */

    private Node treeSuccessor(Node node){
        if(node.getRightSon()!=sentinel)
            return treeMinimum(node.getRightSon());
        Node result = node.getParent();
        while(result!= sentinel && node == result.getRightSon()){
            node = result;
            result = result.getParent();
        }
        return result;
    }

    private StringBuilder build(StringBuilder lineBuilder, boolean isTail, StringBuilder sb, Node subRoot, int counter) {
        Node rightSon = subRoot.getRightSon();
        Node leftSon = subRoot.getLeftSon();
        StringBuilder newLineBuilder;
        if(rightSon!=sentinel) {
            newLineBuilder = new StringBuilder();
            newLineBuilder.append(lineBuilder);
            if(counter==0 || !isTail)
                newLineBuilder.append("    ");
            else
                newLineBuilder.append("|   ");
            build(newLineBuilder, false, sb,rightSon,++counter);
        }
        if(subRoot == this.getRoot())
            sb.append(lineBuilder).append("    ").append(subRoot).append("\n");
        else
            sb.append(lineBuilder).append("+-- ").append(subRoot).append("\n");
        if(leftSon!=sentinel) {
            newLineBuilder = new StringBuilder();
            newLineBuilder.append(lineBuilder);
            if(counter==0 || isTail)
                newLineBuilder.append("    ");
            else
                newLineBuilder.append("|   ");
            build(newLineBuilder, true, sb,leftSon,counter);
        }
        return sb;
    }

    private String getValueAsString(Node subRoot) {
        return this.build(new StringBuilder(), true, new StringBuilder(),subRoot,0).toString();
    }

    @Override
    public String toString(){
        return this.getValueAsString(this.getRoot())+(Utilities.repeat("-",40));

    }
}
