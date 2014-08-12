package com.codehathi.common.hash;

public class MurmurHash2 {

    private static final int m = 0x5bd1e995;
    private static final int r = 24;

    /**
     * Hash a byte
     *
     * @param data byte to hash
     * @return hash of the input data
     */
    public static int hash(byte data) {
        return hash(data, -1);
    }

    /**
     * Hash a byte
     *
     * @param data byte to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(byte data, int seed) {
        int hash = seed ^ 1;
        hash = (hash ^ (int) data) * m;
        hash = ((hash ^ (hash >>> 13)) * m);
        return hash ^ (hash >>> 15);
    }

    /**
     * Hash a short
     *
     * @param data short to hash
     * @return hash of the input data
     */
    public static int hash(short data) {
        return hash(data, -1);
    }

    /**
     * Hash a short
     *
     * @param data short to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(short data, int seed) {
        int hash = seed ^ 2;

        byte b1 = (byte) ((data >> 8) & 0xFF);
        byte b2 = (byte) ((data) & 0xFF);

        hash ^= (int) b1 << 8;
        hash = (hash ^ (int) b2) * m;
        hash = ((hash ^ (hash >>> 13)) * m);

        return hash ^ (hash >>> 15);
    }

    /**
     * Hash an int
     *
     * @param data int to hash
     * @return hash of the input data
     */
    public static int hash(int data) {
        return hash(data, -1);
    }

    /**
     * Hash an int
     *
     * @param data int to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(int data, int seed) {
        int hash = seed ^ 4;

        byte b1 = (byte) ((data >> 24));
        byte b2 = (byte) ((data >> 16));
        byte b3 = (byte) ((data >> 8));
        byte b4 = (byte) ((data));

        int k = b4 << 8;
        k = (k | (b3 & 0xFF)) << 8;
        k = (k | (b2 & 0xFF)) << 8;
        k = (k | (b1 & 0xFF)) * m;
        k = (k ^ (k >>> r)) * m;
        hash = (hash * m) ^ k;

        hash = ((hash ^ (hash >>> 13)) * m);
        return hash ^ (hash >>> 15);
    }

    /**
     * Hash a float
     *
     * @param data float to hash
     * @return hash of the input data
     */
    public static int hash(float data) {
        return hash(Float.floatToIntBits(data), -1);
    }

    /**
     * Hash a float
     *
     * @param data float to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(float data, int seed) {
        return hash(Float.floatToIntBits(data), seed);
    }

    /**
     * Hash a long
     *
     * @param data long to hash
     * @return hash of the input data
     */
    public static long hash(long data) {
        return hash(data, -1);
    }

    /**
     * Hash a long
     *
     * @param data long to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(long data, int seed) {
        int hash = seed ^ 8;

        byte b1 = (byte) (data >> 56);
        byte b2 = (byte) (data >> 48);
        byte b3 = (byte) (data >> 40);
        byte b4 = (byte) (data >> 32);

        int k = b4 << 8;
        k = (k | (b3 & 0xFF)) << 8;
        k = (k | (b2 & 0xFF)) << 8;
        k = (k | (b1 & 0xFF)) * m;
        k = (k ^ (k >>> r)) * m;
        hash = (hash * m) ^ k;

        b1 = (byte) (data >> 24);
        b2 = (byte) (data >> 16);
        b3 = (byte) (data >> 8);
        b4 = (byte) data;

        k = b4 << 8;
        k = (k | (b3 & 0xFF)) << 8;
        k = (k | (b2 & 0xFF)) << 8;
        k = (k | (b1 & 0xFF)) * m;
        k = (k ^ (k >>> r)) * m;
        hash = (hash * m) ^ k;

        hash = ((hash ^ (hash >>> 13)) * m);
        return hash ^ (hash >>> 15);
    }

    /**
     * Hash a double
     *
     * @param data double to hash
     * @return hash of the input data
     */
    public static int hash(double data) {
        return hash(Double.doubleToLongBits(data), -1);
    }

    /**
     * Hash a double
     *
     * @param data double to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(double data, int seed) {
        return hash(Double.doubleToLongBits(data), seed);
    }

    /**
     * Hash a byte array
     *
     * @param data byte array to hash
     * @return hash of the input data
     */
    public static int hash(byte[] data) {
        return hash(data, data.length, -1);
    }

    /**
     * Hash a byte array
     *
     * @param data byte array to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(byte[] data, int seed) {
        return hash(data, data.length, seed);
    }

    /**
     * Hash a byte array
     *
     * @param data byte array to hash
     * @param len  length of the byte array to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(byte[] data, int len, int seed) {

        int hash = seed ^ len;

        int upper = (len & (~0x3));
        for (int i = 0; i < upper; i += 4) {
            int k = data[i + 3] << 8;
            k = (k | (data[i + 2] & 0xff)) << 8;
            k = (k | (data[i + 1] & 0xff)) << 8;
            k = (k | (data[i + 0] & 0xff)) * m;
            k = (k ^ (k >>> r)) * m;
            hash = (hash * m) ^ k;
        }

        int left = len & 0x3;

        switch (left) {
            case 4:
            case 3:
                hash ^= (int) data[len - 3] << 16;
            case 2:
                hash ^= (int) data[len - 2] << 8;
            case 1:
                hash ^= (int) data[len - 1];
                hash *= m;
                break;
        }

        hash = ((hash ^ (hash >>> 13)) * m);
        return hash ^ (hash >>> 15);
    }

    /**
     * Hash a char
     *
     * @param data char to hash
     * @return hash of the input data
     */
    public static int hash(char data) {
        return hash((int) data, -1);
    }

    /**
     * Hash a char
     *
     * @param data char to hash
     * @param seed hash seed
     * @return hash of the input data
     */
    public static int hash(char data, int seed) {
        return hash((int) data, seed);
    }


    /**
     * Hashes a String.
     * <p/>
     * Warning: this is not a good implementation since it hashes the {@link String#hashCode()}
     *
     * @param s String to hash
     * @return murmur hash
     */
    public static int hash(String s) {
        return hash(s.hashCode(), -1);
    }

    /**
     * Hashes a String.
     * <p/>
     * Warning: this is not a good implementation since it hashes the {@link String#hashCode()}
     *
     * @param s    String to hash
     * @param seed hash seed
     * @return murmur hash
     */
    public static int hash(String s, int seed) {
        return hash(s.hashCode(), seed);
    }

    /**
     * Hashes an object
     * <p/>
     * Warning: this is not a good implementation since it hashes the {@link Object#toString()#hashCode()}
     *
     * @param o String to hash
     * @return murmur hash
     */
    public static int hash(Object o) {
        return hash(o.toString());
    }

    /**
     * Hashes an object
     * <p/>
     * Warning: this is not a good implementation since it hashes the {@link Object#toString()#hashCode()}
     *
     * @param o String to hash
     * @return murmur hash
     */
    public static int hash(Object o, int seed) {
        return hash(o.toString(), seed);
    }
}
