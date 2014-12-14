package eting.repository;

import eting.EtingApplication;
import eting.domain.Device;
import eting.domain.Incognito;
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
public class IncognitoRepositoryTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    @Test
    public void create() {


        // given 1
        Device device1 = new Device();
        device1.setUuid("16b72c7c-6d8d-471d-9615-bb06d40ea748");
        device1.setRegDt(new Date());
        device1.setOs("A");
        device1.setPushKey("APA91bFrp1f8U1WfpB62vCVDX3qEv8SThBGng5yfpQwM3jk9pLuSijjMPpejp-1MSSulynAYjjwWkrTHxueS0MH8bMWRf4kIQcMLtW8LHKrH76MachQs_OL7AEE2c-PR0VmnIvnctfZXkTplxj69I0LuEBYB5Ch2vg");

        // when
        deviceRepository.save(device1);

        // then
        assertThat(deviceRepository.findAll().size(), is(1));

        // given 1
        Incognito incognito1 = new Incognito();
        incognito1.setIncognitoId(device1.getDeviceId());

        // when
        incognitoRepository.save(incognito1);

        // then
        assertThat(incognitoRepository.findAll().size(), is(1));

        //find all
        List<Incognito> all = incognitoRepository.findAll();
        for(Incognito d : all ){
            System.out.println(d);
        }
    }
}