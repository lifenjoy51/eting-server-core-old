package eting.service;

import eting.domain.Exchange;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 14. 12. 24.
 */
@Service
public class ExchangeService {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    StoryQueueService storyQueueService;

    /**
     * insert story to exchange queue.
     * @param story
     */
    public void insertQueue(Story story){

        //save exchange info
        Exchange exchange = new Exchange(story);
        exchangeRepository.save(exchange);

        //save story and incognito info!
        storyQueueService.insert(story);
    }

    /**
     * get story from queue
     * @param incognito
     * @return
     */
    public Story getRandomStory(Incognito incognito){
        //what to return?
        Story story = storyQueueService.get(incognito);
        //re insert into queue.
        //story will be deleted when replied or reported.
        storyQueueService.insert(story);

        //check write incognito.
        if(story.getIncognitoId() == incognito.getIncognitoId()){
            //TODO what to sendback.
            story = null;
        }

        return story;
    }


    /**
     * pass story
     *
     * @param exchange
     */
    public void passStory(Exchange exchange) {
        // 이야기 대기열에 있는지, 없는지 판단할 필요가 있나?
        // 금지어에 걸린 이야기라면.... 금지어 대기열에 다시 넣는건 어떠한가?
        // 그러면 금지어 대기열이 늘어나는데... 금지어 대기열은 어디서 빼지?
        // 지금 기본적으로 대기열이 빠질 수 있는 구멍이 적다.
        // 다른 이야기들은 별을 누르고 추가로 받아오는 로직으로 대기열을 빼고 있는데
        // 금지어대기열은 하나 넣고 하나 빼는 방식이기 때문에 줄어들지 않는다...
        // 그러면 이야기를 전송하는건 어떠한가???
        // 패스를 하면 대기열에 있는지 검사하고 답글이 달려있는지 검사해서??
        // 쓰레기를 빼낼 곳이 필요하다.....

        // 패스당한걸 다시 금지어를 검사하고, 그룹 대기열에 다시 넣는다!
        // 그리고 메세지를 한개 보낸다!

    }

    /**
     * report story
     *
     * @param exchange
     */
    public void reportStory(Exchange exchange) {

        // 대기열에서 삭제한다.

        //140521
        //신고하는 사람이 신고당했는지 검사한다.
        //if(!junkService.isReportedDevice(reportedStory.getDevice_id())){

        // 신고정보에 입력한다.

        // 신고당한사람 차단여부 판별

        //}
    }

}
