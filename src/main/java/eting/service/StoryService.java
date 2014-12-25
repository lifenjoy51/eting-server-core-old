package eting.service;

import eting.domain.Exchange;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.StoryPK;
import eting.helper.DuplicationHelper;
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
    DuplicationHelper duplicationHelper;

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

        // 기기정보가 없으면 처리하지 않는다.

        // savedStory에 기기고유번호를 넣는다.

        // 14.03.22 기기 그룹 설정

        // 내용 체크

        // 금지어 검사로직
        // 금지어가 들어가있으면 해당 유저의 prohibit_type을 바꾼다.

        // 금지어 그룹 업데이트

        // 무의미한 단어 검사 .
        // 이야기의 story_prohibit_type을 T로하고
        // device_group을 T로 바꾼다.

        // 해당 기기가 바로 전에 입력한 이야기를 가져온다.
        // TODO 2014.03.12 이야기를 전송하고도 목록에 뜨지 않는 버그때문에 우선 주석처리..
        // 이전에 저장한 이야기와 현재 저장할 이야기가 같으면 작업을 종료한다.
        duplicationHelper.isDuplicated(story);

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
}
