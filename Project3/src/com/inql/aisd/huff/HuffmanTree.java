package com.inql.aisd.huff;

import com.inql.aisd.huff.io.BitInputStream;
import com.inql.aisd.huff.io.BitOutputStream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


public class HuffmanTree {

    private class Node implements Comparable<Node> {
        private final Integer letter;
        private final int frequency;
        private final Node leftSon;
        private final Node rightSon;

        public Node(Integer letter, int frequency) {
            this.leftSon = null;
            this.rightSon = null;
            this.letter = letter;
            this.frequency = frequency;
        }

        public Node(Node leftSon, Node rightSon) {
            this.leftSon = leftSon;
            this.rightSon = rightSon;
            this.letter = null;
            this.frequency = leftSon.frequency + rightSon.frequency;
        }

        public Node(LinkedList<Integer> letterQueue, BitInputStream inputStream) throws IOException {
            if (inputStream.readBit()) {
                letter = letterQueue.pop();
                frequency = 0;
                leftSon = null;
                rightSon = null;
            }
            else {
                letter = null;
                frequency = 0;
                leftSon = new Node(letterQueue, inputStream);
                rightSon = new Node(letterQueue, inputStream);
            }
        }


        @Override
        public int compareTo(Node t) {
            return this.frequency - t.frequency;
        }

        public boolean isLeaf() {
            return (letter != null);
        }

        public void read(BitInputStream bitInputStream, OutputStream outputStream) throws IOException {
            if(!this.isLeaf()) {
                try {
                    if (bitInputStream.readBit()) {
                        rightSon.read(bitInputStream, outputStream);
                    }
                    else {
                        leftSon.read(bitInputStream, outputStream);
                    }
                }
                catch (EOFException e) {
                    throw e;
                }
            }
            else
                outputStream.write(this.letter);
        }

        public void fillPathMap(Map<Integer, Boolean[]> pathMap) {
            fillPathMap(pathMap, new Boolean[0]);
        }

        private void fillPathMap(Map<Integer, Boolean[]> pathMap, Boolean[] prefix) {
            if (this.isLeaf()) {
                pathMap.put(this.letter, prefix);
            }
            else {
                Boolean[] lowerAddress = Arrays.copyOf(prefix, prefix.length + 1);
                lowerAddress[lowerAddress.length - 1] = false;
                leftSon.fillPathMap(pathMap, lowerAddress);

                Boolean[] higherAddress = Arrays.copyOf(prefix, prefix.length + 1);
                higherAddress[higherAddress.length - 1] = true;
                rightSon.fillPathMap(pathMap, higherAddress);
            }
        }

        public void writeLetters(BitOutputStream outputStream, ArrayList<Integer> letterMap) throws IOException {
            if (this.isLeaf()) {
                outputStream.writeBinary(letterMap.indexOf(this.letter), getLastBit(letterMap.size() - 1));
                letterMap.remove(this.letter);
            }
            else {
                leftSon.writeLetters(outputStream, letterMap);
                rightSon.writeLetters(outputStream, letterMap);
            }
        }

        public void writePaths(BitOutputStream outputStream) throws IOException {
            if (!this.isLeaf()) {
                outputStream.writeBit(false);
                leftSon.writePaths(outputStream);
                outputStream.writeBit(true);
                rightSon.writePaths(outputStream);
            }
        }

        public int countLeaves() {
            if (this.isLeaf()) {
                return 1;
            }
            else {
                return leftSon.countLeaves() + rightSon.countLeaves();
            }
        }
    }

    private Node rootNode;
    private Map<Integer, Boolean[]> pathMap;
    private Map<Integer, Integer> frequencyMap;

    public void printPathMap(){
        System.out.printf("-----------------------------------------------------\n" +
                "|    znak    |    czestosc    |    kod Huffmana     |\n" +
                "-----------------------------------------------------\n");
        for (Integer value :
                pathMap.keySet()) {
            StringBuilder binCode = new StringBuilder();
            Boolean[] path = pathMap.get(value);
            for(int i =0; i<path.length; i++){
                if(path[i])
                    binCode.append("1");
                else
                    binCode.append("0");
            }
            System.out.printf("|%12c|%16d|%21s|\n",(char) value.intValue(),frequencyMap.get(value),binCode.toString());
        }
    }

    public HuffmanTree(Map<Integer, Integer> frequencyMap) {
        initFromFrequencyMap(frequencyMap);
    }

    public HuffmanTree(BitInputStream bitInputStream) throws IOException {
        this.initFromInputStream(bitInputStream);
    }

    private void initFromInputStream(BitInputStream bitInputStream) throws IOException{
        int letterCount = bitInputStream.read() + 1;

        ArrayList<Integer> lettersMap = getLetterMap();
        LinkedList<Integer> letterQueue = new LinkedList<>();

        for (int i = 0; i < letterCount; i++) {
            int letterIndex = bitInputStream.readBinary(getLastBit(lettersMap.size() - 1));

            letterQueue.add(lettersMap.get(letterIndex));
            lettersMap.remove(letterIndex);
        }

        rootNode = new Node(letterQueue, bitInputStream);
    }

    private void initFromFrequencyMap(Map<Integer, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;

        if (frequencyMap.size() < 2) {
            throw new IllegalArgumentException("The frequency map must have at least two element.");
        }

        ArrayList<Node> nodeList = getNodeList(frequencyMap);
        Collections.sort(nodeList);

        while (nodeList.size() > 1) {
            nodeList.add(new Node(nodeList.remove(0), nodeList.remove(0)));
            Collections.sort(nodeList);
        }

        assert(nodeList.size() > 0);

        this.rootNode = nodeList.get(0);

        pathMap = new TreeMap<>();
        this.rootNode.fillPathMap(pathMap);
    }

    private ArrayList<Node> getNodeList(Map<Integer, Integer> frequencyMap) {
        ArrayList<Node> nodeList = new ArrayList<>();

        for (int key : frequencyMap.keySet()) {
            nodeList.add(new Node(key, frequencyMap.get(key)));
        }

        Collections.sort(nodeList);
        return nodeList;
    }

    public void read(BitInputStream compressedInputStream, OutputStream decompressedOutputStream) throws IOException {
        try {
            while (true) {
                rootNode.read(compressedInputStream, decompressedOutputStream);
            }
        }
        catch (EOFException ex) {
            // finished
        }
    }

    public void write(InputStream uncompressedInputStream, BitOutputStream compressedOutputStream) throws IOException {
        int readByte;

        while ((readByte = uncompressedInputStream.read()) >= 0) {
            for (boolean value : pathMap.get(readByte)) {
                compressedOutputStream.writeBit(value);
            }
        }
    }

    public void serializeTo(BitOutputStream outputStream) throws IOException {
        int letterCount = rootNode.countLeaves();
        outputStream.write(letterCount - 1);

        ArrayList<Integer> lettersMap = getLetterMap();

        rootNode.writeLetters(outputStream, lettersMap);
        rootNode.writePaths(outputStream);
        //dummy bit to know eof
        outputStream.writeBit(true);
    }

    private ArrayList<Integer> getLetterMap() {
        ArrayList<Integer> lettersMap = new ArrayList<>(256);

        for (int i = 0; i < 256; i++) {
            lettersMap.add(i);
        }
        return lettersMap;
    }


    private int getLastBit(int value) {
        int result = 0;

        while (value > 0) {
            value >>= 1;
            result++;
        }

        return result;
    }
}
