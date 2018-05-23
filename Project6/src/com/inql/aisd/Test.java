package com.inql.aisd;

import com.inql.aisd.algorithms.Testable;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Test {

    private char[] text;
    private char[] pattern;

    public Test(char[] text, char[] pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    public String doTest(Testable testable){
        System.out.println("--------------------------------------------------------------");
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
        long start = System.nanoTime();
        testable.run(text,pattern);
        long stop = System.nanoTime();
        BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);
        String result = decimalFormat.format(bigDecimal);
        System.out.print(result+"\n");
        System.out.println("--------------------------------------------------------------");
        return result;
    }
}
