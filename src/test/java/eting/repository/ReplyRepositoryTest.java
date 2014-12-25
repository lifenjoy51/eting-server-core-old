package eting.repository;

import eting.EtingApplication;
import eting.TestConfig;
import eting.domain.*;
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
public class ReplyRepositoryTest {


    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testStory(){
        Device device = insertDevice(); //두개 기기 입력
        Incognito incognito = insertIncognito(device);  //두명 익명사용자 입력
        Story story = insertStory(incognito);   //이야기 하나 입력
        Story storyFound = findStory(story);
        Exchange exchange = insertExchange(story);  //이야기 교환 입력.
        Reply reply = insertReply(exchange);
    }

    private Reply insertReply(Exchange exchange) {
        Reply reply = new Reply();
        reply.setStoryIncognitoId(exchange.getStoryIncognitoId());
        reply.setStoryDt(exchange.getStoryDt());
        reply.setIncognitoId(exchange.getIncognitoId());
        reply.setReplyDt(Util.getDt());
        reply.setReplyContent("reply content test...");
        reply.setEmoticonList("1");

        System.out.println(reply);

        //when
        replyRepository.save(reply);

        // then
        assertThat(replyRepository.findAll().size(), is(1));

        return reply;
    }

    private Exchange insertExchange(Story story) {
        Exchange exchange = new Exchange();
        exchange.setStoryIncognitoId(story.getIncognitoId());
        exchange.setStoryDt(story.getStoryDt());
        exchange.setIncognitoId(story.getIncognitoId()+1L);
        exchange.setStatus("SR");

        System.out.println(exchange);

        //when
        exchangeRepository.save(exchange);

        // then
        //assertThat(exchangeRepository.findAll().size(), is(1));

        for(Exchange e : exchangeRepository.findAll()){
            System.out.println(e);
        }

        return exchange;
    }

    private Story findStory(Story story) {

        long id = story.getIncognitoId();
        long utime = story.getStoryDt().getTime();
        Date dt = new Date(utime);

        StoryPK pk = new StoryPK(id, dt);

        return storyRepository.findOne(pk);
    }

    public Story insertStory(Incognito incognito){
        Story story = new Story();
        story.setIncognitoId(incognito.getIncognitoId());
        story.setStoryDt(Util.getDt());
        story.setStoryContent("test story...");
        story.setStoryType("N");

        System.out.println("date >> ");
        System.out.println(story.getStoryDt().getTime());

        //when
        storyRepository.save(story);

        // then
        //assertThat(storyRepository.findAll().size(), is(1));

        for(Story s : storyRepository.findAll()){
            System.out.println(s);
        }

        return story;
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

        // given 2
        Device device2 = new Device();
        device2.setUuid("06498a95-03b2-4c41-8482-28519b9a9688");
        device2.setRegDt(new Date());
        device2.setOs("A");
        device2.setPushKey("APA91bFJK3D4Srd3pr2RuZyNTWHKOSV836iADsy6p8Fg2qsfrf5BUYlZY5cgBl6f078MsEaHYuABhOfubds50zG7ssqZ6K7hEny1DK6AmvqOt3ZWNjGB7qQ_DYH5Dba_QyL-FxB7KCgDLQtDdodmXLl3ULUmVJx1fQ");

        // when
        deviceRepository.save(device2);

        // then
        //assertThat(deviceRepository.findAll().size(), is(2));


        assertTrue(deviceRepository.findAll().size() > 0);

        for(Device d : deviceRepository.findAll()){
            System.out.println(d);
        }

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

        // given 1
        Incognito incognito2 = new Incognito();
        incognito2.setIncognitoId(device.getDeviceId()+1L);

        // when
        incognitoRepository.save(incognito2);

        return incognito;

    }
}