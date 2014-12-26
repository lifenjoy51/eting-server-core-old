package eting.service;

import eting.TestConfig;
import eting.TestUtil;
import eting.domain.*;
import eting.domain.pk.StoryPK;
import eting.service.ExchangeService;
import eting.service.IncognitoService;
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
        writeStory(tom);

        //queue
        printQueue();
    }

    private Story getStory(Incognito james) {
        return exchangeService.getRandomStory(james);
    }

    private Story writeStory(Incognito incognito) {

        Story story = new Story(incognito);
        story.setIncognitoId(incognito.getIncognitoId());
        story.setStoryDt(Util.getDt());
        story.setStoryContent(TestUtil.randomStoryText());
        story.setStoryType("N");

        story = storyService.saveStory(story);
        return story;
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