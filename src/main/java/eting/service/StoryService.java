package eting.service;

import eting.domain.Exchange;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.StoryPK;
import eting.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by lifenjoy51 on 14. 12. 22.
 */
@Service
public class StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    ExchangeService exchangeService;

    /**
     * save story to DB.<br>
     * and insert to exchange queue.
     * @param story
     */
    public void saveStory(Story story){

        //save story
        storyRepository.save(story);

        //insert exchange queue
        exchangeService.insertQueue(story);

    }


    /**
     * get story
     * @param pk
     * @return
     */
    public Story getStory(StoryPK pk) {
        return storyRepository.findOne(pk);
    }
}
