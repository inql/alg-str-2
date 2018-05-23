package com.inql.aisd;

import com.inql.aisd.algorithms.Kmp;
import com.inql.aisd.algorithms.Naive;
import com.inql.aisd.algorithms.RabinKarp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {


    public static void main(String[] args) {
	// write your code here
        if(args.length!=2){
            System.out.println("Wrong number of arguments, only 2 allowed!");
            return;
        }
        try{
            char[] pattern = initializeDataArray(args[0]);
            char[] text = initializeDataArray(args[1]);
            Naive naive = new Naive();
            RabinKarp rabinKarp = new RabinKarp();
            Kmp kmp = new Kmp();
            Test test = new Test(text,pattern);
            String[] results = new String[3];
            results[0] = test.doTest(naive);
            results[1] = test.doTest(rabinKarp);
            results[2] = test.doTest(kmp);
            System.out.println("\n\n\n\n\nPODSUMOWANIE:");
            System.out.println("Naiwny - " + results[0]);
            System.out.println("rabinKarp - " + results[1]);
            System.out.println("KMP - " + results[2]);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int getInputArrayLength(String filename) throws IOException {
        File file = new File(filename);
        int counter = 0;
        if(!file.exists()){
            throw new FileNotFoundException(filename+ " plik nie istnieje");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        char current;
        while (fileInputStream.available()>0){
            current = (char) fileInputStream.read();
            if(current != '\n')
                counter++;
        }
        fileInputStream.close();
        return counter;
    }

    private static char[] initializeDataArray(String filename) throws IOException {
        File file = new File(filename);
        int index = 0;
        char[] result = new char[getInputArrayLength(filename)];
        FileInputStream fileInputStream = new FileInputStream(file);
        char current;
        while (fileInputStream.available()>0){
            current = (char) fileInputStream.read();
            if(current!='\n')
                result[index++] = current;
        }
        return result;
    }









}
