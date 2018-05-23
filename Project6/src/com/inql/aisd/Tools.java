package com.inql.aisd;

public class Tools {

    public static boolean loopThrough(char[] patternData, char[] textData,int m, int s){
        for(int i = 0; i<m; i++){
            if(patternData[i]!=textData[s+i]){
                return false;
            }
        }
        return true;
    }

}
