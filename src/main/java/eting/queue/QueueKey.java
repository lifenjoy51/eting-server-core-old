package eting.queue;

import eting.domain.Incognito;
import eting.domain.Story;
import lombok.Data;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
@Data
public class QueueKey {

    private final String lang;

    private final String queueType;

    /**
     * generate key by given story.
     *
     * @param story
     */
    public QueueKey(Story story) {

        //language.
        Incognito incognito = story.getIncognito();
        this.lang = incognito.getLang();

        //determine queue type by eting group.
        String etingGroup = incognito.getEtingGroup();
        if(!"N".equalsIgnoreCase(etingGroup)){
            //when group is not normal.
            this.queueType = etingGroup;
        }else{
            //use story type
            this.queueType = story.getStoryType();
        }

    }

    /**
     * generate key by given incognito.
     *
     * @param incognito
     */
    public QueueKey(Incognito incognito) {

        //language.
        this.lang = incognito.getLang();

        //determine queue type by eting group.
        String etingGroup = incognito.getEtingGroup();
        if(!"N".equalsIgnoreCase(etingGroup)){
            //when group is not normal.
            this.queueType = etingGroup;
        }else{
            //determine queue type by eting type.
            //when group is normal.
            String etingType = incognito.getEtingType();
            this.queueType = etingType;
        }
    }
}
