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
    public Story saveStory(Story story){

        //save story
        Story savedStory = storyRepository.save(story);

        //insert exchange queue
        //must pass saved story.
        exchangeService.insertQueue(savedStory);

        return savedStory;

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
