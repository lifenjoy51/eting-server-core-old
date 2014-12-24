package eting.service;

import eting.domain.User;
import eting.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 14. 12. 14.
 */
@Service
public class UserService {

    private static final Log logger = LogFactory.getLog(UserService.class);

    @Autowired
    StandardPasswordEncoder standardPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    /**
     * new user.
     * @param user
     */
    public void addUser(User user){
        //password encryption
        user.setUserPw(standardPasswordEncoder.encode(user.getUserPw()));
        logger.debug(user.getUserPw());
        userRepository.save(user);
    }

    /**
     * verify user.
     * @param userId
     * @param userPw
     * @return
     */
    public boolean maches(String userId, String userPw){
        User user = userRepository.findOne(userId);
        return standardPasswordEncoder.matches(userPw, user.getUserPw());
    };
}
