package eting.service;

import eting.EtingApplication;
import eting.TestConfig;
import eting.domain.Device;
import eting.domain.Incognito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class IncognitoServiceTest {

    @Autowired
    IncognitoService incognitoService;

    public Incognito testRegistration() throws Exception {
        // given 1
        Device device1 = new Device();
        device1.setUuid("16b72c7c-6d8d-471d-9615-bb06d40ea748");
        device1.setRegDt(new Date());
        device1.setOs("A");
        device1.setPushKey("APA91bFrp1f8U1WfpB62vCVDX3qEv8SThBGng5yfpQwM3jk9pLuSijjMPpejp-1MSSulynAYjjwWkrTHxueS0MH8bMWRf4kIQcMLtW8LHKrH76MachQs_OL7AEE2c-PR0VmnIvnctfZXkTplxj69I0LuEBYB5Ch2vg");

        // when
        Incognito incognito1 = incognitoService.registration(device1);

        // then
        Assert.notNull(incognito1.getIncognitoId());

        return incognito1;

    }

    @Test
    public void testGet() throws Exception {
        Incognito incognito1 = testRegistration();
        Incognito incognito2 = incognitoService.get(incognito1.getIncognitoId());

        Assert.notNull(incognito2.getIncognitoId());

        System.out.println(incognito2);

    }

    @Test
    public void testUpdate() throws Exception {
        Incognito incognito1 = testRegistration();
        System.out.println(incognito1);
        incognito1.setBirthYear(1988);

        incognitoService.update(incognito1);
        Incognito incognito2 = incognitoService.get(incognito1.getIncognitoId());

        Assert.notNull(incognito2.getIncognitoId());
        System.out.println(incognito2);

    }
}