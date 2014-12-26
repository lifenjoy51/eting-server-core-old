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

        // savedStory에 기기고유번호를 넣는다.

        // 14.03.22 기기 그룹 설정

        // exit if content is empty.
        if(isEmpty(story)) {
            return null;
        }

        // 금지어 검사로직
        // 금지어가 들어가있으면 해당 유저의 prohibit_type을 바꾼다.
        handleStoryType(story);

        // 무의미한 단어 검사 .
        // 이야기의 story_prohibit_type을 T로하고
        // device_group을 T로 바꾼다.

        // 해당 기기가 바로 전에 입력한 이야기를 가져온다.
        // TODO 2014.03.12 이야기를 전송하고도 목록에 뜨지 않는 버그때문에 우선 주석처리..
        // 이전에 저장한 이야기와 현재 저장할 이야기가 같으면 작업을 종료한다.
        duplicationService.isDuplicated(story);

        // 이야기를 저장한다.




        // 비속어 검사로직
        // 유통차단 금지어그룹일 경우
        // 똥일 경우
        // 140508 이야기가 중복이면 대기열에 넣지 않음!! && 무의미한 이야기가 아닌경우만!
        // && 특수필터 (톡아이디)


        //save story
        Story savedStory = storyRepository.save(story);

        //insert exchange queue
        //must pass saved story.
        exchangeService.insertQueue(savedStory);

        return savedStory;



    }

    /**
     * update user info if eting type not equal to story type.
     *
     * @param story
     */
    private void handleStoryType(Story story) {

        Incognito incognito = story.getIncognito();

        //story type.
        String storyType = storyTypeService.getType(story);

        //skip if type is equal.
        if(storyType.equals(incognito.getEtingType())) return;

        //or update
        incognito.setEtingType(storyType);
        incognitoService.update(incognito);

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
