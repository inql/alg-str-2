import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanTree<T> {
    private Node root;
    private Map<T, String> pathMap;
    private Map<T, Integer> frequencyMap;

    public HuffmanTree(Map<T,Integer> frequencyMap){
        this.frequencyMap = frequencyMap;

        ArrayList<Node> nodeArrayList = getNodeList(frequencyMap);
        Collections.sort(nodeArrayList);

        while(nodeArrayList.size()>1){
            nodeArrayList.add(new Node(nodeArrayList.remove(0),nodeArrayList.remove(0)));
            Collections.sort(nodeArrayList);
        }

        assert (nodeArrayList.size()>0);

        this.root = nodeArrayList.get(0);

        pathMap = new TreeMap<>();
        this.root.fillPathMap(pathMap);
    }

    private ArrayList<Node> getNodeList(Map<T, Integer> frequencyMap) {
        ArrayList<Node> nodeList = new ArrayList<>();

        for (T key : frequencyMap.keySet()) {
            nodeList.add(new Node(key, frequencyMap.get(key)));
        }

        Collections.sort(nodeList);
        return nodeList;
    }

    private void printPathMap(){
        System.out.printf("-----------------------------------------------------\n" +
                "|    znak    |    czestosc    |    kod Huffmana     |\n" +
                "-----------------------------------------------------\n");
        for (T value :
                pathMap.keySet()) {
            String binCode = pathMap.get(value);
            if(value instanceof IntPair || value instanceof IntTrio)
                System.out.printf("|%12s|%16d|%21s|\n",value.toString(),frequencyMap.get(value),binCode);
            if(value instanceof Integer)
                System.out.printf("|%12s|%16d|%21s|\n",(char) ((Integer) value).intValue(),frequencyMap.get(value),binCode);

        }
    }

    public void printSummary(File file){
//        printPathMap();
        int stableCodeLength = generateStableCodeLength(frequencyMap);
        int huffCodeLength = generateHuffCodeLength(frequencyMap,pathMap);
        long fileLength = file.length();
        System.out.println("Długość pliku wiadomości zakodowanej:\n\t1) Kodami stałej długości: \n\t\t"+stableCodeLength+"b\n\t\t"+(double) stableCodeLength/8.0+"B");
        System.out.println("\t2) Kodem Huffmana: \n\t\t"+huffCodeLength+"b\n\t\t"+(double) huffCodeLength / 8.0+"B");
        System.out.println("\t3) Rozmiar pliku: \n\t\t"+fileLength*Byte.SIZE+"b\n\t\t"+fileLength+"B");
    }

    public int generateStableCodeLength(Map<T, Integer> frequencyMap){
        int bitSize = (int) Math.ceil(Math.log(frequencyMap.size())/Math.log(2));
        int result = 0;
        for(T value: frequencyMap.keySet()){
            result+=bitSize*frequencyMap.get(value);
        }
        return result;
    }

    public int generateHuffCodeLength(Map<T,Integer> frequencyMap, Map<T,String> pathMap){
        int result = 0;
        for(T value: frequencyMap.keySet()){
            result+=pathMap.get(value).length()*frequencyMap.get(value);
        }
        return result;
    }



}
