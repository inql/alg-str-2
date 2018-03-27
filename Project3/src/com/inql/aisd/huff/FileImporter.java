package com.inql.aisd.huff;

import com.inql.aisd.huff.model.MutableInt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileImporter {

    String path;

    public FileImporter(String path) {
        this.path = path;
    }

    public Map<Character, MutableInt> importFile(){
        Map<Character, MutableInt> counter = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))){

            stream.map(line -> line.split(""))
                    .flatMap(Arrays::stream)
                    .forEach(s -> {
                        MutableInt value = counter.get(s.charAt(0));
                        if (value == null){
                            counter.put(s.charAt(0),new MutableInt());
                        }
                        else{
                            value.increment();
                        }
                    });
            counter.forEach((k,v) -> System.out.println(k.toString() + counter.get(k)));


        }catch (IOException e){
            e.printStackTrace();
        }
        return counter;
    }
}
