package eting.service;

import eting.code.ExchangeStatus;
import eting.domain.Device;
import eting.domain.Exchange;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.queue.StoryQueue;
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
    StoryQueue storyQueue;

    /**
     * insert story to exchange queue.
     * @param story
     */
    public void insertQueue(Story story){

        //save exchange info
        Exchange exchange = new Exchange(story);
        exchangeRepository.save(exchange);

        //save story and incognito info!
        storyQueue.insert(story);
    }

    /**
     * get story from queue
     * @param incognito
     * @return
     */
    public Story getRandomStory(Incognito incognito){
        //what to return?
        return storyQueue.get();
    }

}
