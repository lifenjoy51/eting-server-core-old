package eting;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import static org.junit.Assert.assertTrue;

/**
 * Created by lifenjoy51 on 14. 12. 13.
 */
public class Tester {
    public static void main(String[] args){
        StandardPasswordEncoderTest encoder = new StandardPasswordEncoderTest("secret");
        String result = encoder.encode("myPassword");

        System.out.println("#result");
        //String result = "12e3cb309d4b211f3734b8291e84ca32b712fc1f57aafab1d44114404c09da92c043e62ea1ad292b";
        System.out.println(result);
        boolean match = encoder.matches("myPassword", result);
        System.out.println(match);

                
    }
}
