package com.inql.aisd.algorithms;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static com.inql.aisd.Tools.loopThrough;

public class Naive implements Testable{

    private void naiveSearch(char[] patternData, char[] textData){
        int pattenDataLength = patternData.length;
        int textDataLength = textData.length;
        for(int i = 0; i<=textDataLength-pattenDataLength; i++){
            if(loopThrough(patternData,textData,pattenDataLength,i))
                System.out.println("Znalezione wystąpienie wzorca od pozycji: "+(i+1)+" koniec wzorca na: "+(i+pattenDataLength));
        }
    }

    @Override
    public void run(char[] text, char[] pattern) {
        System.out.println("\n-----------------------TESTY DLA NAIVE-----------------------");
        naiveSearch(pattern,text);
    }
}
