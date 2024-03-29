package eting.service;

import eting.TestConfig;
import eting.domain.Device;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.pk.StoryPK;
import eting.repository.DeviceRepository;
import eting.repository.IncognitoRepository;
import eting.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class StoryServiceTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    @Autowired
    StoryService storyService;

    @Test
    public void testStory(){
        Device device = insertDevice();
        Incognito incognito = insertIncognito(device);
        Story story = insertStory(incognito);
        Story storyFound = findStory(story);
        System.out.println(storyFound);
        System.out.println("date >> ");
        System.out.println(storyFound.getStoryDt().getTime());
    }

    private Story findStory(Story story) {

        long id = story.getIncognitoId();
        long utime = story.getStoryDt().getTime();
        Date dt = new Date(utime);

        StoryPK pk = new StoryPK(id, dt);

        return storyService.getStory(pk);
    }

    public Device insertDevice() {

        // given 1
        Device device1 = new Device();
        device1.setUuid("16b72c7c-6d8d-471d-9615-bb06d40ea748");
        device1.setRegDt(Util.getDt());
        device1.setOs("A");
        device1.setPushKey("APA91bFrp1f8U1WfpB62vCVDX3qEv8SThBGng5yfpQwM3jk9pLuSijjMPpejp-1MSSulynAYjjwWkrTHxueS0MH8bMWRf4kIQcMLtW8LHKrH76MachQs_OL7AEE2c-PR0VmnIvnctfZXkTplxj69I0LuEBYB5Ch2vg");

        // when
        deviceRepository.save(device1);

        // then
       // assertThat(deviceRepository.findAll().size(), is(1));

        return device1;

    }

    public Incognito insertIncognito(Device device){

        // given 1
        Incognito incognito = new Incognito();
        incognito.setIncognitoId(device.getDeviceId());

        // when
        incognitoRepository.save(incognito);

        // then
        //assertThat(incognitoRepository.findAll().size(), is(1));

        return incognito;

    }

    public Story insertStory(Incognito incognito){
        Story story = new Story(incognito);
        story.setIncognitoId(incognito.getIncognitoId());
        story.setStoryDt(Util.getDt());
        story.setStoryContent("test story...");
        story.setStoryType("N");

        System.out.println("date >> ");
        System.out.println(story.getStoryDt().getTime());

        //when
        storyService.save(story);


        return story;
    }
}