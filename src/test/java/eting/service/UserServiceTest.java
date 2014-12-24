package eting.service;

import eting.EtingApplication;
import eting.TestConfig;
import eting.domain.User;
import eting.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testUserService() throws Exception {
        testAddUser();
        testMatches();
    }

    public void testAddUser() throws Exception {

        // given 1
        User user1 = new User();
        user1.setUserId("lifenjoy51");
        user1.setUserPw("passwd");
        user1.setRegDt(new Date());

        // when
        userService.addUser(user1);

        System.out.println(user1);

        // then
        assertThat(userRepository.findAll().size(), is(1));
    }

    public void testMatches(){
        //when
        String userId = "lifenjoy51";
        String userPw = "passwd";
        boolean matches = userService.maches(userId, userPw);
        assertThat(matches, is(true));

    }

}