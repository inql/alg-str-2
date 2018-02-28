package com.inql.aisd.redblack;

import com.inql.aisd.Utilities;

import java.util.ArrayList;
import static com.inql.aisd.redblack.Color.*;

public class RedBlackTree {

    private class Node implements Comparable<Node>{
        private Integer value;
        private Node leftSon = sentinel;
        private Node rightSon = sentinel;
        private Node parent = sentinel;
        private Color color = RED;
        private int counter = 1;

        Node(Integer value) {
            this.value = value;
        }

        private void increaseCounter(){
            this.counter++;
        }

        private void decreaseCounter(){
            this.counter--;
        }

        @Override
        public int compareTo(Node o) {
            if(o!=null && o.value!=null) return this.value - o.value;
            return -this.value;
        }

        @Override
        public String toString() {
            return
                    color + "v:" + value +
                            "c:" + counter +
                            "\u001B[0m";
        }
    }

    private final Node sentinel = new Node(null);
    private Node root = sentinel;

    public Node getSentinel() {
        return sentinel;
    }

    /**
     *
     * @param toRotate - wezel do obrocenia
     */

    private void leftRotate(Node toRotate){
        Node rightSon = toRotate.rightSon;
        toRotate.rightSon = rightSon.leftSon;
        if(rightSon.leftSon!=sentinel)
            rightSon.leftSon.parent = toRotate;
        rightSon.parent = toRotate.parent;
        if(toRotate.parent == sentinel)
            root = rightSon;
        else if (toRotate == toRotate.parent.leftSon)
            toRotate.parent.leftSon = rightSon;
        else toRotate.parent.rightSon = rightSon;
        rightSon.leftSon = toRotate;
        toRotate.parent = rightSon;
    }

    /**
     *
     * @param toRotate - wezel do obrocenia
     */

    private void rightRotate(Node toRotate){
        Node leftSon = toRotate.leftSon;
        toRotate.leftSon = leftSon.rightSon;
        if(leftSon.rightSon!=sentinel)
            leftSon.rightSon.parent = toRotate;
        leftSon.parent = toRotate.parent;
        if(toRotate.parent==sentinel)
            root = leftSon;
        else if(toRotate == toRotate.parent.rightSon)
            toRotate.parent.rightSon = leftSon;
        else
            toRotate.parent.leftSon = leftSon;
        leftSon.rightSon = toRotate;
        toRotate.parent = leftSon;
    }
    /**
     * Metoda wypisuje zawartość drzewa w postaci preoreder
     */
    public void preorderPrint() {
        preorderPrint(root, maxDepth());
    }

    private void preorderPrint(Node x, int counter) {
        if (x == sentinel)
            return;
        System.out.println(Utilities.repeat(" ",counter)+x);
        postorderPrint(x.leftSon, --counter);
        counter++;
        postorderPrint(x.rightSon, --counter);

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
        postorderPrint(x.leftSon, --counter);
        counter++;
        postorderPrint(x.rightSon, --counter);
        System.out.println(Utilities.repeat(" ",counter)+x);

    }

    /**
     * Metoda wypisuje zawartość drzewa w postaci inorder
     */
    public void inorderPrint() {
        inorderPrint(root, maxDepth());
    }

    private void inorderPrint(Node x, int counter) {
        if (x == sentinel)
            return;
        inorderPrint(x.leftSon, --counter);
        System.out.println(Utilities.repeat(" ",counter)+x);
        counter++;
        inorderPrint(x.rightSon, --counter);

    }

    /**
     * Umieszcza wartość do drzewa czerwono-czarnego
     * @param value - wartość do umieszczenia
     */

    public void redBlackInsert(Integer value){
        redBlackInsert(new Node(value));
    }

    private void redBlackInsert(Node node){
        if(insertNode(node).counter!=1)
            return;
        Node uncle;
        while(node != root && node.parent.color== RED){
            if(node.parent == node.parent.parent.leftSon){
                uncle = node.parent.parent.rightSon;
                if(uncle != sentinel && uncle.color== RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.rightSon) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotate(node.parent.parent);
                }
            }
            else{
                uncle = node.parent.parent.leftSon;
                if(uncle != sentinel && uncle.color == RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.leftSon) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }
    /**
     * Metoda <code>insertNode</code> umieszcza węzeł w argumencie jawnym do drzewa, zachowując zasady drzew binarnych
     * @param node - węzeł do umieszczenia
     *
     */

    private Node insertNode(Node node) {
        Node current = root;
        Node nodesFather = sentinel;
        while (current != sentinel) {
            nodesFather = current;
            if(node.compareTo(current)==0) {
                current.increaseCounter();
                return current;
            }
            else if (node.compareTo(current)<0) current = current.leftSon;
            else if (node.compareTo(current)>0) current = current.rightSon;
        }
        node.parent = nodesFather;
        if (nodesFather == sentinel)
            root = node;
        else if (node.compareTo(nodesFather)<0)
            nodesFather.leftSon = node;
        else
            nodesFather.rightSon = node;
        return node;
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

    /**
     * Usuwa wartość z drzewa czerwono/czarnego
     * @param value - wartość do usunięcia (uprzednio wyszukiwana za pomocą <code>treeSearch</code>
     * @return znaleziony węzeł - jeśli istnieje - w przeciwnym wypadku zwraca wartownika (sentinel)
     */

    public Node redBlackDelete(int value){
        Node toDelete = treeSearch(value,root);
        if(toDelete!=sentinel){
            if(toDelete.counter==1){
                return redBlackDelete(toDelete);
            }
                toDelete.decreaseCounter();
                return toDelete;
        }
        return sentinel;
    }

    private Node redBlackDelete(Node toDelete){
        Node x,y;
        if(toDelete.leftSon == sentinel || toDelete.rightSon == sentinel)
            y = toDelete;
        else
            y = treeSuccessor(toDelete);
        if(y.leftSon != sentinel)
            x = y.leftSon;
        else{
            x = y.rightSon;
        }

        x.parent = y.parent;
        if(y.parent == sentinel)
            this.root = x;
        else {
            if (y == y.parent.leftSon)
                y.parent.leftSon = x;
            else
                y.parent.rightSon = x;
        }
        if(y!=toDelete){
            toDelete.value = y.value;
            toDelete.counter = y.counter;
        }
        if(y.color==BLACK)
            redBlackDeleteFixUp(x);
        return y;
    }

    /**
     * Metoda naprawia drzewo czerwono/czarne po usunięciu elementu
     * @param toFix poddrzewo od ktorego zaczynamy naprawianie
     */

    private void redBlackDeleteFixUp(Node toFix){
        Node w;
        while(toFix != root && toFix.color==BLACK){
            if(toFix == toFix.parent.leftSon){
                w = toFix.parent.rightSon;
                if(w.color==RED){
                    w.color = BLACK;
                    toFix.parent.color = RED;
                    leftRotate(toFix.parent);
                    w = toFix.parent.rightSon;
                }
                if(w.leftSon.color == BLACK && w.rightSon.color == BLACK){
                    w.color = RED;
                    toFix = toFix.parent;
                }
                else
                    if (w.rightSon.color == BLACK) {
                        w.color = RED;
                        rightRotate(w);
                        w = toFix.parent.rightSon;
                    }
                    w.color = toFix.parent.color;
                    toFix.parent.color = BLACK;
                    w.rightSon.color = BLACK;
                    leftRotate(toFix.parent);
                    toFix = root;
            }
            else{
                w = toFix.parent.leftSon;
                if(w.color==RED){
                    w.color = BLACK;
                    toFix.parent.color = RED;
                    rightRotate(toFix.parent);
                    w = toFix.parent.leftSon;
                }
                if(w.rightSon.color == BLACK && w.leftSon.color == BLACK){
                    w.color = RED;
                    toFix = toFix.parent;
                }
                else
                    if (w.leftSon.color == BLACK) {
                        w.color = RED;
                        leftRotate(w);
                        w = toFix.parent.leftSon;
                    }
                    w.color = toFix.parent.color;
                    toFix.parent.color = BLACK;
                    w.leftSon.color = BLACK;
                    rightRotate(toFix.parent);
                    toFix = root;

            }
        }
        toFix.color = BLACK;
    }

    public Node treeSearch(int value){
        Node node = treeSearch(value, root);
        if(node == sentinel){
            System.out.println("Not found.");
        }
        return node;
    }
    /**
     * Metoda <code>treeSearch</code> wyszukuje podaną wartość w drzewie
     * @param value - wartość do wyszukania
     * @param root - korzeń poddrzewa od którego zaczynamy szukanie
     * @return - zwraca referencję do obiektu Node lub null jeśli wartość nie znajduje się w drzewie
     */

    private Node treeSearch(int value, Node root){
        Node current = root;
        while(current != sentinel && current.value!=value){
            if(value <  current.value)
                current = current.leftSon;
            else
                current = current.rightSon;
        }
        return current;
    }
    /**
     * Zwraca głębokość drzewa
     * @return - głębokość drzewa
     */

    private int maxDepth(){
        return maxDepth(root);
    }

    private int maxDepth(Node node) {
        if (node == sentinel) return 0;
        return 1 + Math.max(maxDepth(node.leftSon), maxDepth(node.rightSon));
    }
    /**
     * Metoda <code>treeMinimum</code> zwraca skrajny lewy węzeł
     * w poddrzewie o korzeniu x, czyli węzeł o najmniejszym kluczu w tym poddrzewie
     * @param x - korzeń poddrzewa
     * @return zwraca najmniejszą wartość
     */

    private Node treeMinimum(Node x){
        while(x.leftSon!=sentinel){
            x = x.leftSon;
        }
        return x;
    }

    /**
     * Wyznacza następnik danego węzła
     * @param node - dany węzeł
     * @return następnik
     */

    private Node treeSuccessor(Node node){
        if(node.rightSon!=sentinel)
            return treeMinimum(node.rightSon);
        Node result = node.parent;
        while(result!= sentinel && node == result.rightSon){
            node = result;
            result = result.parent;
        }
        return result;
    }

    public void treeDelete(){
        this.root = sentinel;
    }

    /**
     * Ładne drukowanie drzewa
     * @param lineBuilder - buduje aktualną linię horyzontalnie
     * @param isTail - sprawdza czy wezel jest liściem
     * @param sb - wynik koncowy
     * @param subRoot - poddrzewo ktore sprawdzamy
     * @param counter - licznik pomagajacy uniknac rysowania niepotrzebnych linii
     * @return sb - wynik koncowy
     */

    private StringBuilder build(StringBuilder lineBuilder, boolean isTail, StringBuilder sb, Node subRoot, int counter) {
        Node rightSon = subRoot.rightSon;
        Node leftSon = subRoot.leftSon;
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
        if(subRoot == this.root)
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
        return this.getValueAsString(this.root);

    }
}
