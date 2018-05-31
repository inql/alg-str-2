package com.inql.aisd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInit {

    public static int[][] initializeDataArray(String filename) throws IOException{
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder len = new StringBuilder();
        int length = fileInputStream.read();
        while(length!='\n'){
            len.append(Character.getNumericValue(length));
            length = fileInputStream.read();
        }
        int matrixLength = Integer.parseInt(len.toString());
        int[][] matrix = new int[matrixLength][matrixLength];
        int current;
        int i,j;
        i=0;
        while(fileInputStream.available()>0){
            j=0;
            while(j<matrixLength) {
                current = Character.getNumericValue(fileInputStream.read());
                if(current!=-1){
                    matrix[i][j++]=(current);
                }
            }
            i++;
        }
        return matrix;
    }

}
