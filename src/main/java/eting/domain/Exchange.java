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
}
