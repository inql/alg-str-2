package com.inql.aisd.huff;

import com.inql.aisd.huff.io.BitInputStream;
import com.inql.aisd.huff.io.BitOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;


public class Compressor {

    public static void compress(byte[] value, OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(byteArrayOutputStream);

        HuffmanTree huffmanTree = new HuffmanTree(getFrequencyMap(value));
        huffmanTree.serializeTo(bitOutputStream);

        huffmanTree.write(new ByteArrayInputStream(value), bitOutputStream);

        int trashLength = (bitOutputStream.getTrashLength() - 3 + 8) % 8;
        bitOutputStream.writeBinary(0xFF, bitOutputStream.getTrashLength());
        bitOutputStream.flush();

        BitOutputStream finalBitOutputStream = new BitOutputStream(outputStream);
        finalBitOutputStream.writeBinary(trashLength, 3);
        finalBitOutputStream.writeBinary(0x7F, trashLength);
        finalBitOutputStream.write(byteArrayOutputStream.toByteArray());
        huffmanTree.printPathMap();
        finalBitOutputStream.flush();
    }

    public static void decompress(InputStream inputStream, OutputStream outputStream) throws IOException {
        BitInputStream bitInputStream = new BitInputStream(inputStream);

        // trash used to end the content at a complete byte end.
        int trashLength = bitInputStream.readBinary(3);

        // trash content should be only ones.
        int trashContent = bitInputStream.readBinary(trashLength);

        assert(trashContent == (1 << trashLength) - 1);

        // content
        HuffmanTree tree = new HuffmanTree(bitInputStream);
        tree.read(bitInputStream, outputStream);
    }

    private static Map<Integer, Integer> getFrequencyMap(byte[] value) {
        Map<Integer, Integer> frequencyMap = new TreeMap<>();

        for (byte letter : value) {
            //taking only necessary bits
            int letterValue = letter & 0x007F;

            if (letter < 0) {
                //making unsigned signed
                letterValue = letterValue | 0x0080;
            }

            if (frequencyMap.containsKey(letterValue)) {
                frequencyMap.put(letterValue, frequencyMap.get(letterValue) + 1);
            }
            else {
                frequencyMap.put(letterValue, 1);
            }
        }

        return frequencyMap;
    }
}

