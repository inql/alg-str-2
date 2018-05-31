package com.inql.aisd.algorithms;

import static com.inql.aisd.Tools.loopThrough;

public class RabinKarp implements Testable{

    private static final int ALPHABET_SIZE = 128;
    private static final int PRIME_NUMBER = 27077;

    private void rabinKarp(char[] patternData, char[] textData){
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

    private int hash(char[] source, int begin, int end){
        int h = 0;
        for (int i = begin; i < end; i++){
            h = (ALPHABET_SIZE*h + source[i]) % PRIME_NUMBER;
        }
        return h;
    }

    @Override
    public void run(char[] text, char[] pattern) {
        System.out.println("\n-----------------------TESTY DLA RABIN-----------------------");
        rabinKarp(pattern,text);
    }
}
