package eting.service;

import eting.domain.StoryType;
import eting.util.ContentUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lifenjoy51 on 14. 12. 26.
 */
@Service
public class StoryTypeService {

    private static List<StoryType> storyTypeList;
    private List<String> blockedTypeList;

    public StoryTypeService() {
        blockedTypeList = new ArrayList<String>();
        updateStoryTypeList();
    }

    public boolean isBlock(String storyType) {
        if(blockedTypeList.contains(storyType)) return true;
        return false;
    }

    /**
     * determine type of story content.
     * TODO test!!
     *
     * @param content
     * @return
     */
    public String getType(String content) {

        //금지어를 포함하고 있는지 여부
        int cnt = 0;
        int contentLength = content.length();
        int limitCnt = contentLength / 100;
        String type = "N";

        //140510 정규식으로 전환
        for (StoryType storyType : storyTypeList) {

            //check contains word.
            if (content.contains(storyType.getWord())) {

                //find reg exp.
                Matcher m = storyType.getP().matcher(content);
                if (m.find()) {

                    cnt++;
                    String block = storyType.getBlock();

                    if (cnt > limitCnt || block.equals("Y")) {
                        type = storyType.getType();
                        return type;
                    }
                }
            }
        }

        //check normal content.
        // 무의미한 단어 검사 .
        if(!ContentUtil.isNormalContent(content)) type="T";

        return type;
    }

    @Scheduled(cron = "0 0 */1 * * *")
    private void updateStoryTypeList() {
        storyTypeList = new ArrayList<StoryType>();

        for (StoryType storyType : storyTypeList) {
            storyType.setP(Pattern.compile(storyType.getRegExp()));
            if("Y".equals(storyType.getBlock())){
                blockedTypeList.add(storyType.getType());
            }
        }
    }
}
