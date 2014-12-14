package eting;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.crypto.util.EncodingUtils.concatenate;
import static org.springframework.security.crypto.util.EncodingUtils.subArray;

/**
 * Created by lifenjoy51 on 14. 12. 13.
 */
public class StandardPasswordEncoderTest implements PasswordEncoder {

    private final Digester digester;

    private final byte[] secret;

    private final BytesKeyGenerator saltGenerator;

    /**
     * Constructs a standard password encoder with a secret value which is also included in the
     * password hash.
     *
     * @param secret the secret key used in the encoding process (should not be shared)
     */
    public StandardPasswordEncoderTest(CharSequence secret) {
        this("SHA-256", secret);
    }

    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, saltGenerator.generateKey());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("#matches");
        byte[] digested = decode(encodedPassword);
        System.out.println("digested \n"+new String(Hex.encode(digested)));
        byte[] salt = subArray(digested, 0, saltGenerator.getKeyLength());
        System.out.println("salt \n"+new String(Hex.encode(salt)));
        return matches(digested, digest(rawPassword, salt));
    }

    // internal helpers

    private StandardPasswordEncoderTest(String algorithm, CharSequence secret) {
        this.digester = new Digester(algorithm, DEFAULT_ITERATIONS);
        this.secret = Utf8.encode(secret);
        this.saltGenerator = KeyGenerators.secureRandom();
    }

    private String encode(CharSequence rawPassword, byte[] salt) {
        byte[] digest = digest(rawPassword, salt);
        System.out.println("#encoded");
        System.out.println(new String(Hex.encode(digest)));
        return new String(Hex.encode(digest));
    }

    private byte[] digest(CharSequence rawPassword, byte[] salt) {
        byte[] rawByte = Utf8.encode(rawPassword);
        //print.
        System.out.println("#digest");
        System.out.println("salt \t"+new String(Hex.encode(salt)));
        System.out.println("secret \t"+new String(Hex.encode(secret)));
        System.out.println("pass \t"+new String(Hex.encode(rawByte)));
        byte[] concatenated =  concatenate(salt, secret, rawByte);
        byte[] digest = digester.digest(concatenated);
        return concatenate(salt, digest);
    }

    private byte[] decode(CharSequence encodedPassword) {
        return Hex.decode(encodedPassword);
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private boolean matches(byte[] expected, byte[] actual) {
        System.out.println("##matches");
        System.out.println("expected "+new String(Hex.encode(expected)));
        System.out.println("actual "+new String(Hex.encode(actual)));

        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    private static final int DEFAULT_ITERATIONS = 1024;

}

