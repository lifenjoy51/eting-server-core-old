package eting;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lifenjoy51 on 14. 12. 13.
 */
final class Digester {

    private final MessageDigest messageDigest;

    private final int iterations;

    /**
     * Create a new Digester.
     * @param algorithm the digest algorithm; for example, "SHA-1" or "SHA-256".
     * @param iterations the number of times to apply the digest algorithm to the input
     */
    public Digester(String algorithm, int iterations) {
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }

        this.iterations = iterations;
    }

    public byte[] digest(byte[] value) {
        synchronized (messageDigest) {
            for (int i = 0; i < iterations; i++) {
                value = messageDigest.digest(value);
            }
            return value;
        }
    }
}

