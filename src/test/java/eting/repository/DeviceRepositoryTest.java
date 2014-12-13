package eting.repository;

import eting.EtingApplication;
import eting.domain.Device;
import eting.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EtingApplication.class)
@Transactional
public class DeviceRepositoryTest {
    @Autowired
    DeviceRepository deviceRepository;

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

        // given 2
        Device device2 = new Device();
        device2.setUuid("06498a95-03b2-4c41-8482-28519b9a9688");
        device2.setRegDt(new Date());
        device2.setOs("A");
        device2.setPushKey("APA91bFJK3D4Srd3pr2RuZyNTWHKOSV836iADsy6p8Fg2qsfrf5BUYlZY5cgBl6f078MsEaHYuABhOfubds50zG7ssqZ6K7hEny1DK6AmvqOt3ZWNjGB7qQ_DYH5Dba_QyL-FxB7KCgDLQtDdodmXLl3ULUmVJx1fQ");

        // when
        deviceRepository.save(device2);

        // then
        assertThat(deviceRepository.findAll().size(), is(2));


        //find all
        List<Device> all = deviceRepository.findAll();
        for(Device d : all ){
            System.out.println(d);
        }
    }
}
