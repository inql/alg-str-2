package com.inql.aisd.huff;

import com.inql.aisd.huff.model.MutableInt;

import java.io.File;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Usage - java -jar huffman.jar \"filename\"");
        else {
            try {
                File file = new File(args[0]);
                String path = file.getAbsolutePath();
                System.out.println(path);
                FileImporter fileImporter = new FileImporter(path);
                Map<Character, MutableInt> counter = fileImporter.importFile();
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}