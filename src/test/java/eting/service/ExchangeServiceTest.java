package eting.service;

import eting.TestConfig;
import eting.TestUtil;
import eting.domain.*;
import eting.util.Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class ExchangeServiceTest {


    @Autowired
    IncognitoService incognitoService;

    @Autowired
    StoryService storyService;

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    StoryQueueService storyQueueService;

    //remember data.
    Map<Long, Incognito> userList = new HashMap<Long, Incognito>();

    @Test
    public void testExchange() {
        //add 3 incognito.
        Incognito james = newDevice();
        Incognito tom = newDevice();
        Incognito amy = newDevice();

        //first. james write story.
        Story jamesW1 = writeStory(james);
        System.out.println("jamesW1 " + jamesW1);
        Story jamesG1 = getStory(james);
        System.out.println("jamesG1 " + jamesG1);

        //then tom write story.
        Story tomW1 = writeStory(tom);
        System.out.println("tomW1 " + tomW1);
        Story tomG1 = getStory(tom);
        System.out.println("tomG1 " + tomG1);

        //amy
        Story amyW1 = writeStory(amy);
        System.out.println("amyW1 " + amyW1);
        Story amyG1 = getStory(amy);
        System.out.println("amyG1 " + amyG1);

        //queue
        printQueue();
    }

    private Story getStory(Incognito incognito) {
        return exchangeService.getRandomStory(incognito);
    }

    private Story writeStory(Incognito incognito) {

        sleep();
        Story story = new Story(incognito);
        story.setIncognitoId(incognito.getIncognitoId());
        story.setStoryDt(Util.getDt());
        story.setStoryContent(TestUtil.randomStoryText());
        story.setStoryType("N");

        story = storyService.save(story);
        return story;
    }

    private void sleep() {
        //sleep 1 second.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Incognito newDevice() {

        // device
        Device device = new Device();
        device.setUuid(UUID.randomUUID().toString());
        device.setRegDt(Util.getDt());
        device.setOs("A");
        device.setPushKey(RandomStringUtils.randomAscii(160));
        System.out.println(device);

        //incognito
        Incognito incognito = incognitoService.registration(device);
        incognito.setEtingGroup("N");
        incognito.setEtingType("N");
        incognito.setLang("KR");
        incognitoService.update(incognito);

        userList.put(incognito.getIncognitoId(), incognito);

        return incognito;
    }

    private void printQueue() {
        storyQueueService.print();
    }

}