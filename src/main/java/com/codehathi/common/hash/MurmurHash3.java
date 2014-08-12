package com.codehathi.common.hash;


public class MurmurHash3 {
    private static final int c1 = 0xcc9e2d51;
    private static final int c2 = 0x1b873593;
    private static final int r1 = 15;
    private static final int r2 = 13;
    private static final int m = 5;
    private static final int n = 0xe6546b64;

    public static int hash(int data) {
        return hash(data, -1);
    }

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

    public static int hash(long data) {
        return hash(data, -1);
    }

    public static int hash(long data, int seed) {
        int hash = seed;

        int k = (int)((data >> 32) & 0xFFFFFFFF);
        k *= c1;
        k = (k << r1) | (k >> (32 - r1));
        k *= c2;

        hash ^= k;
        hash = ((hash << r2) | (hash >> (32 - r2))) * m + n;

        k = (int)(data & 0xFFFFFFFF);
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

    public static int hash(byte[] data) {
        return hash(data, data.length, -1);
    }

    public static int hash(byte[] data, int seed){
        return hash(data, data.length, seed);
    }

    public static int hash(byte[] data, int len, int seed) {
        int hash = seed;

        int upper = (len & (~0x3));
        for (int i = 0; i < upper; i+=4) {
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
