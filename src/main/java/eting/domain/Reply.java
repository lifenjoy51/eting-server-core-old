package eting.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifenjoy51 on 14. 12. 15.
 */
@Data
@Entity
@IdClass(ExchangePK.class)
@Table(name = "reply")
public class Reply {

    @Id
    @Column(name="story_incognito_id")
    private long storyIncognitoId;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date storyDt;

    @Id
    @Column(name="incognito_id")
    private long incognitoId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date replyDt;

    private String replyContent;

    private String emoticonList;
}
