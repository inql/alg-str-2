
/*
 based on BitIO
 */

package com.inql.aisd.huff.io;
import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream extends OutputStream {
    private OutputStream outputStream;
    private int currentByte = 0;
    private int currentBits = 0;

    public BitOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeBit(final boolean value) throws IOException {
        currentBits++;
        currentByte <<= 1;

        if (value)
            currentByte += 1;

        if (currentBits == 8) {
            outputStream.write(currentByte);
            currentBits = 0;
            currentByte = 0;
        }
    }

    public void writeBinary(final int value, final int numBits) throws IOException {
        if (numBits + currentBits < 8) {
            currentByte <<= numBits;
            currentByte |= (value) & ((1 << numBits) - 1);
            currentBits += numBits;
        } else {
            // Write the top part
            final int bitsWritten = 8 - currentBits;
            currentByte <<= bitsWritten;
            currentByte |= (value >> (numBits - bitsWritten)) & ((1 << bitsWritten) - 1);
            outputStream.write(currentByte);

            // Write whole bytes
            final int bitsRemaining = numBits - bitsWritten;
            final int bytesToWrite = bitsRemaining / 8;
            final int bitOffset = bitsRemaining % 8;
            for (int i = bytesToWrite - 1; i >= 0; --i) {
                outputStream.write((value >> ((i << 3) + bitOffset)) & 0xFF);
            }

            // Write the bottom part
            currentByte = value & ((2 << bitOffset) - 1);
            currentBits = bitOffset;
        }
    }

    @Override
    public void close() throws IOException {
        if (currentBits > 0) {
            currentByte <<= 8 - currentBits;
            outputStream.write(currentByte);
            outputStream.close();
        }
    }

    @Override
    public void write(int value) throws IOException {
        if (currentBits == 0)
            outputStream.write(value);
        else {
            writeBinary(value, 8);
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    public int getTrashLength() {
        return (8 - currentBits) % 8;
    }
}
