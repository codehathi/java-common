package com.codehathi.common.hash;


public class MurmurHash3 {
    private static final int c1 = 0xcc9e2d51;
    private static final int c2 = 0x1b873593;
    private static final int r1 = 15;
    private static final int r2 = 13;
    private static final int m = 5;
    private static final int n = 0xe6546b64;

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
        int hash = seed;
        int k1 = 0;
        k1 ^= (int) data;
        k1 *= c1;
        k1 = (k1 << r1) | (k1 >> (32 - r1));
        k1 *= c2;
        hash ^= k1;

        hash ^= 1;
        hash ^= (hash >> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >> 16);
        return hash;
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
        int hash = seed;
        int k1 = 0;

        byte b1 = (byte) ((data >> 8) & 0xFF);
        byte b2 = (byte) ((data) & 0xFF);

        k1 ^= (b1 << 8);
        k1 ^= b2;
        k1 *= c1;
        k1 = (k1 << r1) | (k1 >> (32 - r1));
        k1 *= c2;
        hash ^= k1;

        hash ^= 2;
        hash ^= (hash >> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >> 16);

        return hash;
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
        int hash = seed;

        int k = data * c1;
        k = (k << r1) | (k >> (32 - r1));
        k *= c2;

        hash ^= k;
        hash = ((hash << r2) | (hash >> (32 - r2))) * m + n;

        hash ^= 4;
        hash ^= (hash >> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >> 16);

        return hash;
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
    public static int hash(long data) {
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
        int hash = seed;

        int k = (int) ((data >> 32) & 0xFFFFFFFF);
        k *= c1;
        k = (k << r1) | (k >> (32 - r1));
        k *= c2;

        hash ^= k;
        hash = ((hash << r2) | (hash >> (32 - r2))) * m + n;

        k = (int) (data & 0xFFFFFFFF);
        k *= c1;
        k = (k << r1) | (k >> (32 - r1));
        k *= c2;

        hash ^= k;
        hash = ((hash << r2) | (hash >> (32 - r2))) * m + n;


        hash ^= 8;
        hash ^= (hash >> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >> 16);

        return hash;
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
        int hash = seed;

        int upper = (len & (~0x3));
        for (int i = 0; i < upper; i += 4) {
            int k = data[i + 0] << 8;
            k = (k | (data[i + 1] & 0xff)) << 8;
            k = (k | (data[i + 2] & 0xff)) << 8;
            k = (k | (data[i + 3] & 0xff));
            k *= c1;
            k = (k << r1) | (k >> (32 - r1));
            k *= c2;

            hash ^= k;
            hash = ((hash << r2) | (hash >> (32 - r2))) * m + n;
        }


        int k1 = 0;

        switch (len & 0x3) {
            case 4:
            case 3:
                k1 ^= (int) data[len - 3] << 16;
            case 2:
                k1 ^= (int) data[len - 2] << 8;
            case 1:
                k1 ^= (int) data[len - 1];
                k1 *= c1;
                k1 = (k1 << r1) | (k1 >> (32 - r1));
                k1 *= c2;
                hash ^= k1;
        }

        hash ^= len;
        hash ^= (hash >> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >> 16);

        return hash;
    }
}
