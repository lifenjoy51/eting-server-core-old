package eting.domain;

import eting.domain.code.ExchangeStatus;
import eting.domain.pk.ExchangePK;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifenjoy51 on 14. 12. 15.
 */
@Data
@Entity
@IdClass(ExchangePK.class)
@Table(name = "exchange")
public class Exchange {

    @Id
    @Column(name="story_incognito_id")
    private long storyIncognitoId;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date storyDt;

    @Id
    @Column(name="incognito_id")
    private long incognitoId;

    private String status;

    /**
     * public constructor.
     */
    public Exchange(){};

    /**
     * used when inserting queue.
     * @param story
     */
    public Exchange(Story story) {
        this(story.getIncognito(),ExchangeStatus.INSERT_QUEUE, story);
    }

    /**
     * who, what action, which story.
     * @param story
     * @param incognito
     * @param exchangeStatus
     */
    public Exchange(Incognito incognito, ExchangeStatus exchangeStatus, Story story) {
        //story info
        this.setStoryIncognitoId(story.getIncognitoId());
        this.setStoryDt(story.getStoryDt());
        //exchange incognito info
        this.setIncognitoId(incognito.getIncognitoId());
        //set status.
        this.setStatus(exchangeStatus.get());
    }
}
