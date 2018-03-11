package com.inql.aisd.lcs;

import java.util.HashSet;
import java.util.Set;

public class Lcs {
    private String x,y;
    private int k,l;

    private int[][] lcsLengths;

    public Lcs(String x, String y){
        this.x = x;
        this.y = y;
        k = x.length();
        l = y.length();
        lcsLengths = new int[k+1][l+1];
    }

    private int[][] computeLcsLength(){
        for(int i=1; i<=k; i++){
            for(int j=1; j<=l; j++){
                if(x.charAt(i-1) == y.charAt(j-1))
                    lcsLengths[i][j] = lcsLengths[i-1][j-1]+1;
                else
                    lcsLengths[i][j] = Math.max(lcsLengths[i-1][j],lcsLengths[i][j-1]);
            }
        }
        return lcsLengths;
    }

    private String printLcs(){
        int i = k, j = l;
        StringBuilder result = new StringBuilder();
        while(i !=0 && j != 0){
            if(x.charAt(i-1) == y.charAt(j-1)){
                result.append(x.charAt(i-1));
                i--;
                j--;
            }
            else if (lcsLengths[i][j-1] >= lcsLengths[i][j]) j--;
            else i--;
        }
        result.reverse();
        return result.append("\n").toString();
    }

    private Set<String> getAllLcs(){
        return getAllLcs(x,y,k,l);
    }

    private Set<String> getAllLcs(String x, String y, int k, int l){
        Set<String> allLcs = new HashSet<>();

        if(k==0 || l==0)
            allLcs.add("");
        else if(x.charAt(k-1) == y.charAt(l-1)){
            for(String lcsString : getAllLcs(x,y,k-1,l-1))
                allLcs.add(lcsString + x.charAt(k-1));
        }
        else{
            if(lcsLengths[k-1][l] >= lcsLengths[k][l-1])
                allLcs.addAll(getAllLcs(x,y,k-1,l));
            if(lcsLengths[k][l-1] >= lcsLengths[k-1][l])
                allLcs.addAll(getAllLcs(x,y,k,l-1));
        }
        return allLcs;
    }

    public void run(){
        System.out.println("LCS Table:");
        StringBuilder result = new StringBuilder();
        result.append("  yj ");
        for(char c : y.toCharArray())
            result.append(c+" ");
        result.append("\n");
        int i = -1;
        for(int[] ext : computeLcsLength()){
            if(i==-1)
                result.append("xi ");
            else
                result.append(x.charAt(i)+"  ");
            for(int val: ext)
                result.append(val+" ");
            i++;
            result.append("\n");
        }
        System.out.println(result);
        System.out.println("length of LCS = "+lcsLengths[k][l]);
        System.out.println(printLcs());
        System.out.println(getAllLcs());
    }
}
