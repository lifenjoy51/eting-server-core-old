package eting.service;

import eting.domain.StoryType;
import eting.util.ContentUtil;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lifenjoy51 on 14. 12. 26.
 */
public class StoryTypeService {

    private static List<StoryType> storyTypeList;
    private List<String> blockedTypeList;

    public boolean isBlock(String storyType) {
        if(blockedTypeList.contains(storyType)) return true;
        return false;
    }

    public String getType(String content) {

        //금지어를 포함하고 있는지 여부
        int cnt = 0;
        int contentLength = content.length();
        int limitCnt = contentLength / 100;

        //140510 정규식으로 전환
        for (StoryType storyType : getStoryTypeList()) {
            if (content.contains(storyType.getWord())) {
                Matcher m = storyType.getP().matcher(content);
                if (m.find()) {
                    String block = storyType.getBlock();
                    cnt++;

                    if (cnt > limitCnt || block.equals("1")) {
                        String type = storyType.getType();
                        return type;
                    }
                }
            }
        }

        //check normal content.
        // 무의미한 단어 검사 .
        // 이야기의 story_prohibit_type을 T로하고
        // device_group을 T로 바꾼다.
        ContentUtil.isNormalContent(content);

        return null;
    }

    private List<StoryType> getStoryTypeList() {
        if(storyTypeList == null) updateStoryTypeList();
        return storyTypeList;
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
