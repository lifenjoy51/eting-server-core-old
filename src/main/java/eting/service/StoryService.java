package eting.service;

import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.pk.StoryPK;
import eting.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 14. 12. 22.
 */
@Service
public class StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    DuplicationService duplicationService;

    @Autowired
    StoryTypeService storyTypeService;

    @Autowired
    IncognitoService incognitoService;

    @Autowired
    ExchangeService exchangeService;


    /**
     * get story
     * @param pk
     * @return
     */
    public Story getStory(StoryPK pk) {
        return storyRepository.findOne(pk);
    }

    /**
     * save story to DB.<br>
     * and insert to exchange queue.
     * @param story
     */
    public Story saveStory(Story story){

        // 기기정보가 없으면 처리하지 않는다. >> this is blocked by security.
        // exit if content is empty.
        if(isEmpty(story)) return null;

        // handle story type.
        boolean block = handleStoryType(story);

        // check duplication.
        boolean dup = duplicationService.isDuplicated(story);

        // 이야기를 저장한다.
        //save story
        Story savedStory = storyRepository.save(story);

        //verify insert into queue or not.
        if(block || dup){
            //do not insert.
            //TODO sth..

        } else {
            //insert exchange queue
            //must pass saved story.
            exchangeService.insertQueue(savedStory);
        }

        return savedStory;
    }

    /**
     * update user info if eting type not equal to story type.
     *
     * @param story
     */
    private boolean handleStoryType(Story story) {

        Incognito incognito = story.getIncognito();

        //story type.
        String storyType = storyTypeService.getType(story.getStoryContent());

        //update incognito if type is not equal.
        if(!storyType.equals(incognito.getEtingType())){
            incognito.setEtingType(storyType);
            incognitoService.update(incognito);
        }

        //is this type blocked? (blocked to insert queue?)
        boolean block = storyTypeService.isBlock(storyType);
        return block;

    }

    /**
     * check story content is empty.
     *
     * @param story
     * @return
     */
    private boolean isEmpty(Story story) {
        String content = story.getStoryContent();
        if(content != null){
            if(content.trim().equals("")){
                return true;
            }
        }
        return false;
    }
}
