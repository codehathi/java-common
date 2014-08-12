package com.codehathi.common.hash;

import org.junit.Test;

import java.nio.ByteBuffer;

public class MurmurHash2Test {

    @Test
    public void testIntegerHash() {

        System.out.println(MurmurHash2.hash(10));

        int data = 100;

        byte[] b = ByteBuffer.allocate(4).putInt(data).array();

        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b, 4, -1));
    }

    @Test
    public void testLongHash() {

        System.out.println(MurmurHash2.hash((long) 10));

        long data = -100;

        byte[] b = ByteBuffer.allocate(8).putLong(data).array();

        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b, 8, -1));
    }

    @Test
    public void testByte() {
        byte data = -5;
        byte[] b = ByteBuffer.allocate(1).put(data).array();

        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b, 1, -1));
    }

    @Test
    public void testShort() {
        short data = -8;
        byte[] b = ByteBuffer.allocate(2).putShort(data).array();

        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b));
    }

    @Test
    public void testFloat() {
        float data = -1.3f;

        byte[] b = ByteBuffer.allocate(4).putFloat(data).array();
        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b, b.length, -1));
    }

    @Test
    public void testDouble() {
        double data = -1.3;

        byte[] b = ByteBuffer.allocate(8).putDouble(data).array();
        System.out.println(MurmurHash2.hash(data));
        System.out.println(MurmurHash2.hash(b, b.length, -1));
    }

    @Test
    public void testBytesHash() {

        byte[] b = new byte[101];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (i & 0xFF);
        }

        System.out.println(MurmurHash2.hash(b, b.length, -1));

    }

}
