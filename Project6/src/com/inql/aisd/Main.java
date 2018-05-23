package com.inql.aisd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final int ALPHABET_SIZE = 128;
    private static final int PRIME_NUMBER = 27077;

    public static void main(String[] args) {
	// write your code here
        if(args.length!=2){
            System.out.println("Wrong number of arguments, only 2 allowed!");
            return;
        }
        File pattern =  new File(args[0]);
        File text = new File(args[1]);
        try{
            byte[] patternData = Files.readAllBytes(Paths.get(pattern.toURI()));
            byte[] textData = Files.readAllBytes(Paths.get(text.toURI()));
            System.out.println((byte)'a');
            naiveSearch(patternData,textData);
            rabinKarp(patternData,textData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void naiveSearch(byte[] patternData, byte[] textData){
        int pattenDataLength = patternData.length;
        int textDataLength = textData.length;
        for(int i = 0; i<=textDataLength-pattenDataLength; i++){
            if(loopThrough(patternData,textData,pattenDataLength,i))
                System.out.println("Znalezione wystąpienie wzorca od pozycji: "+(i+1));
        }
    }

    public static boolean loopThrough(byte[] patternData, byte[] textData,int m, int s){
        for(int i = 0; i<m; i++){
            if(patternData[i]!=10 || textData[s+1]!=10){
                if(patternData[i]!=textData[s+i]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void rabinKarp(byte[] patternData, byte[] textData){
        int h = 1;
        int pattenDataLength = patternData.length;
        int textDataLength = textData.length;
        int max = textDataLength-pattenDataLength;
        for(int i=0; i<pattenDataLength-1; i++){
            h = (h*ALPHABET_SIZE) % PRIME_NUMBER;
        }
        int p = hash(patternData,0,pattenDataLength);
        int t = hash(textData,0,pattenDataLength);
        for(int s = 0; s<max; s++){
            if(p == t)
                if(loopThrough(patternData,textData,pattenDataLength,s))
                    System.out.println("Znalezione wystąpienie wzorca od pozycji "+(s+1)+" koniec wzorca na: "+(s+pattenDataLength));
                int t1 = (textData[s]*h)%PRIME_NUMBER;
                if(t<t1) t=t+PRIME_NUMBER;
                t = (ALPHABET_SIZE*(t-t1)+textData[s+pattenDataLength])%PRIME_NUMBER;
        }
    }

    private static int hash(byte[] source, int begin, int end){
        int h = 0;
        for (int i = begin; i < end; i++){
            h = (ALPHABET_SIZE*h + source[i]) % PRIME_NUMBER;
        }
        return h;
    }
}
