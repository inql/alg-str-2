import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        if(args.length!=1){
            System.out.printf("wrong number of arguments, only 1 allowed!");
            return;
        }
        File inputFile = new File(args[0]);
        try {
            byte[] data = Files.readAllBytes(Paths.get(inputFile.toURI())) ;
            Map<Integer, Integer> integerIntegerMap = getIntegerFrequencyMap(data);
            Map<IntPair,Integer> intPairIntegerMap = getIntPairFrequencyMap(data);
            Map<IntTrio,Integer> intTrioIntegerMap = getIntTrioFrequencyMap(data);
            HuffmanTree<Integer> huffmanTree = new HuffmanTree<>(integerIntegerMap);
            HuffmanTree<IntPair> intPairHuffmanTree = new HuffmanTree<>(intPairIntegerMap);
            HuffmanTree<IntTrio> intTrioHuffmanTree = new HuffmanTree<>(intTrioIntegerMap);
            huffmanTree.printSummary(inputFile);
            intPairHuffmanTree.printSummary(inputFile);
            intTrioHuffmanTree.printSummary(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Integer> getIntegerFrequencyMap(byte[] data) {
        Map<Integer, Integer> frequencyMap = new TreeMap<>();

        for (byte value : data) {
            int intValue = (int) value;
            if (frequencyMap.containsKey(intValue)) {
                frequencyMap.put(intValue, frequencyMap.get(intValue) + 1);
            }
            else {
                frequencyMap.put(intValue, 1);
            }
        }

        return frequencyMap;
    }

    private static Map<IntPair, Integer> getIntPairFrequencyMap(byte[] data){
        Map<IntPair,Integer> frequencyMap = new TreeMap<>();

        for(int i =0; i<data.length; i+=2){
            IntPair intPair;
            if(i==data.length-1)
                 intPair = new IntPair((int)data[i],null);
            else
                intPair = new IntPair((int)data[i],(int)data[i+1]);
            if(frequencyMap.containsKey(intPair)){
                frequencyMap.put(intPair,frequencyMap.get(intPair)+1);
            }
            else{
                frequencyMap.put(intPair,1);
            }
        }
        return frequencyMap;
    }

    private static Map<IntTrio, Integer> getIntTrioFrequencyMap(byte[] data){
        Map<IntTrio,Integer> frequencyMap = new TreeMap<>();

        for(int i =0; i<data.length; i+=3){
            IntTrio intTrio;
            if(i==data.length-1)
                intTrio = new IntTrio((int) data[i],null,null);
            else if(i==data.length-2)
                intTrio = new IntTrio((int)data[i],(int)data[i+1],null);
            else
                intTrio = new IntTrio((int)data[i],(int)data[i+1],(int)data[i+2]);
            if(frequencyMap.containsKey(intTrio)){
                frequencyMap.put(intTrio,frequencyMap.get(intTrio)+1);
            }
            else{
                frequencyMap.put(intTrio,1);
            }
        }
        return frequencyMap;
    }
}
