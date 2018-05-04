/**
 * based on BitIO
 */
package com.inql.aisd.huff.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class BitInputStream extends InputStream {
    private InputStream inputStream;
    private int currentByte;
    private int currentBit = 8;


    public BitInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean readBit() throws IOException {
        if (currentBit == 8) {
            currentBit = 0;
            currentByte = inputStream.read();

            if (currentByte == -1)
                throw new EOFException();
        }

        boolean returnValue = (currentByte & (1 << (7 - currentBit))) != 0;

        currentBit++;

        return returnValue;
    }

    public int readBinary(int numBits) throws IOException {
        if (numBits + currentBit < 8) {
            int offset = 8 - currentBit - numBits;
            currentBit += numBits;
            return (currentByte >> offset) & ((1 << numBits) - 1);
        } else {
            // Read remaining bits in current byte
            int value;
            int bitsRemaining = numBits;
            int bitsRead = 8 - currentBit;
            value = currentByte & ((1 << bitsRead) - 1);
            bitsRemaining -= bitsRead;
            currentBit += bitsRead;

            // Read whole bytes
            int bytesToRead = bitsRemaining / 8;
            for (int i = 0; i < bytesToRead; ++i) {
                currentByte = inputStream.read();

                if (currentByte == -1)
                    throw new EOFException();

                value <<= 8;
                value += currentByte;
            }

            // Read remaining bits
            bitsRemaining = bitsRemaining % 8;
            if (bitsRemaining > 0) {
                currentByte = inputStream.read();
                value <<= bitsRemaining;
                int offset = 8 - bitsRemaining;
                currentBit = bitsRemaining;
                value += (currentByte >> offset) & ((1 << bitsRemaining) - 1);
            }

            return value;
        }
    }

    @Override
    public int read() throws IOException {
        try {
            return readBinary(8);
        }
        catch (EOFException ex) {
            return -1;
        }
    }
}

