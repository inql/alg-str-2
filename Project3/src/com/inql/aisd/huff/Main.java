package com.inql.aisd.huff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java com.inql.aisd.huff.Main c|d inputFile outputFile");
            return;
        }

        try{
            if (args[0].equalsIgnoreCase("c")) {
                File inputFile = new File(args[1]);
                File outputFile = new File(args[2]);

                if (!inputFile.isFile()) {
                    System.err.println("The source file can not be read.");
                    return;
                }

                compress(inputFile, outputFile);
            }
            else if (args[0].equalsIgnoreCase("d")) {
                File inputFile = new File(args[1]);
                File outputFile = new File(args[2]);

                if (!inputFile.isFile()) {
                    System.err.println("The source file can not be read.");
                    return;
                }

                decompress(inputFile, outputFile);
            }
        }
        catch (Exception ex) {
            System.err.println("Operation failed due to:" + ex.getMessage());

            ex.printStackTrace();
        }
        finally {
            try {
                closeStreams();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void compress(File inputFile, File outputFile) throws Exception{

        fileInputStream = new FileInputStream(inputFile);
        byte[] input = new byte[(int)inputFile.length()];
        fileInputStream.read(input);

        fileOutputStream = new FileOutputStream(outputFile);
        Compressor.compress(input, fileOutputStream);
        fileOutputStream.flush();

        System.out.println("Compression completed. Check "+outputFile.getAbsolutePath());
    }

    private static void decompress(File inputFile, File outputFile) throws Exception{
        fileInputStream = new FileInputStream(inputFile);
        fileOutputStream = new FileOutputStream(outputFile);
        Compressor.decompress(fileInputStream, fileOutputStream);
        System.out.println("Decompression completed. Check: "+outputFile.getAbsolutePath());
    }



    private static void closeStreams() throws IOException {
        if (fileInputStream != null) {
            fileInputStream.close();
        }

        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

}