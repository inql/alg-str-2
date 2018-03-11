package com.inql.aisd;

import com.inql.aisd.lcs.Lcs;

public class Main {

    private static String[] args;

    public static void main(String[] args) {
        Lcs lcs = new Lcs(args[0], args[1]);
        lcs.run();
    }
}
