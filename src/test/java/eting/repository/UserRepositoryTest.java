package eting.repository;

import eting.EtingApplication;
import eting.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EtingApplication.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void create() {
        // given 1
        User user1 = new User();
        user1.setUserId("lifenjoy51");
        user1.setUserPw("passwd");
        user1.setRegDt(new Date());

        // when
        userRepository.save(user1);

        // then
        assertThat(userRepository.findAll().size(), is(1));

        // given 2
        User user2 = new User();
        user2.setUserId("osm");
        user2.setUserPw("passpasspasspasspasspasspass");
        user2.setRegDt(new Date());

        // when
        userRepository.save(user2);

        // then
        assertThat(userRepository.findAll().size(), is(2));


        //find all
        List<User> all = userRepository.findAll();
        for(User d : all ){
            System.out.println(d);
        }
    }
}