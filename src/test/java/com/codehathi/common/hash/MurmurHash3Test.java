package com.codehathi.common.hash;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

public class MurmurHash3Test {

    @Test
    public void testIntegerHash() {

        int data = -100;

        byte[] b = ByteBuffer.allocate(4).putInt(data).array();

        Assert.assertEquals(MurmurHash3.hash(b, 4, -1), MurmurHash3.hash(data));
    }

    @Test
    public void testLongHash() {
        long data = -100;

        byte[] b = ByteBuffer.allocate(8).putLong(data).array();

        Assert.assertEquals(MurmurHash3.hash(b, 8, -1), MurmurHash3.hash(data));
    }

    @Test
    public void testByte() {
        for (byte i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            byte[] b = ByteBuffer.allocate(1).put(i).array();
            Assert.assertEquals("Failed for " + i, MurmurHash3.hash(b), MurmurHash3.hash(i));
            if (i == Byte.MAX_VALUE) {
                break;
            }
        }
    }

    @Test
    public void testShort() {
        for (short i = Short.MIN_VALUE; i <= Short.MAX_VALUE; i++) {
            byte[] b = ByteBuffer.allocate(2).putShort(i).array();
            Assert.assertEquals("Failed for " + i, MurmurHash3.hash(b), MurmurHash3.hash(i));
            if (i == Short.MAX_VALUE) {
                break;
            }
        }
    }

    @Test
    public void testFloat() {
        float data = -1.3f;

        byte[] b = ByteBuffer.allocate(4).putFloat(data).array();
        Assert.assertEquals(MurmurHash3.hash(b, b.length, -1), MurmurHash3.hash(data));
    }

    @Test
    public void testDouble() {
        double data = -1.3;

        byte[] b = ByteBuffer.allocate(8).putDouble(data).array();
        Assert.assertEquals(MurmurHash3.hash(b, b.length, -1), MurmurHash3.hash(data));
    }

    @Test
    public void testBytesHash() {

        byte[] b = new byte[301];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (i & 0xFF);
        }

        System.out.println(Integer.toHexString(MurmurHash3.hash(b, b.length, -1)));
    }

}
