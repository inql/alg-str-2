package com.inql.aisd.algorithms;

public class Kmp implements Testable{


    public void kmpAlgorithm(char[] text, char[] pattern){
        int textLength = text.length;
        int patternLength = pattern.length;
        int[] pi = prefixFunction(pattern);
        int q =0;
        for (int i =1; i<=textLength; i++){
            while (q>=0 && pattern[q]!=text[i-1]){
                q=pi[q];
            }
            q++;
            if(q==patternLength){
                System.out.println("Znalezione wystąpienie wzorca od pozycji: "+(i-patternLength+1));
                q=pi[q];
            }
        }
    }

    private int[] prefixFunction(char[] pattern){
        int patternLength = pattern.length;
        int[] pi = new int[patternLength+1];
        int k = -1;
        pi[0] = k;
        for(int q=1; q<patternLength; q++){
            while(k>=0 && pattern[k+1]!= pattern[q]){
                k=pi[k];
            }
            k++;
            pi[q+1]=k;
        }
        return pi;
    }


    @Override
    public void run(char[] text, char[] pattern) {
        System.out.println("\n-----------------------TESTY DLA KMP-----------------------");
        kmpAlgorithm(text, pattern);
    }
}
