package eting.repository;

import eting.EtingApplication;
import eting.domain.Device;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.StoryPK;
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
public class StoryRepositoryTest {


    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    @Autowired
    StoryRepository storyRepository;

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
        StoryPK pk = new StoryPK();
        pk.setIncognitoId(story.getIncognitoId());
        pk.setStoryDt(story.getStoryDt());

        return storyRepository.findOne(pk);
    }

    public Device insertDevice() {

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

        return device1;

    }

    public Incognito insertIncognito(Device device){

        // given 1
        Incognito incognito = new Incognito();
        incognito.setIncognitoId(device.getDeviceId());

        // when
        incognitoRepository.save(incognito);

        // then
        assertThat(incognitoRepository.findAll().size(), is(1));

        return incognito;

    }

    public Story insertStory(Incognito incognito){
        Story story = new Story();
        story.setIncognitoId(incognito.getIncognitoId());
        story.setStoryDt(new Date());
        story.setStoryContent("test story...");
        story.setStoryType("N");

        System.out.println("date >> ");
        System.out.println(story.getStoryDt().getTime());

        //when
        storyRepository.save(story);

        // then
        assertThat(storyRepository.findAll().size(), is(1));

        for(Story s : storyRepository.findAll()){
            System.out.println(s);
        }

        return story;
    }
}