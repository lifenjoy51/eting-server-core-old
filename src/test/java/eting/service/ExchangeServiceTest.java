package eting.service;

import eting.EtingApplication;
import eting.TestConfig;
import eting.domain.*;
import eting.queue.StoryQueue;
import eting.repository.DeviceRepository;
import eting.repository.ExchangeRepository;
import eting.repository.IncognitoRepository;
import eting.util.Util;
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
public class ExchangeServiceTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    @Autowired
    StoryService storyService;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    StoryQueue storyQueue;

    @Test
    public void testExchange(){
        Device device = insertDevice();
        Incognito incognito = insertIncognito(device);
        Story story = insertStory(incognito);
        Story storyFound = findStory(story);
        System.out.println(storyFound);
        System.out.println("date >> ");
        System.out.println(storyFound.getStoryDt().getTime());
        fetchExchanges();
        printQueue();
    }

    private void printQueue() {
        storyQueue.print();
    }

    private void fetchExchanges() {
        for(Exchange e : exchangeRepository.findAll()){
            System.out.println(e);
        }

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
        //assertThat(deviceRepository.findAll().size(), is(1));

        return device1;

    }

    public Incognito insertIncognito(Device device){

        // given 1
        Incognito incognito = new Incognito();
        incognito.setIncognitoId(device.getDeviceId());
        incognito.setEtingGroup("N");
        incognito.setEtingType("N");
        incognito.setLang("KR");

        // when
        incognito = incognitoRepository.save(incognito);

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
        story = storyService.saveStory(story);


        return story;
    }
}