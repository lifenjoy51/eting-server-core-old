package eting.domain;

import eting.code.ExchangeStatus;
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

    public Exchange(){};

    public Exchange(Story story, Incognito incognito) {
        //story info
        this.setStoryIncognitoId(story.getIncognitoId());
        this.setStoryDt(story.getStoryDt());
        //exchange incognito info
        this.setIncognitoId(incognito.getIncognitoId());
    }

    public Exchange(Story story, Incognito incognito, ExchangeStatus exchangeStatus) {
        this(story, incognito);
        this.setStatus(exchangeStatus.get());
    }
}
