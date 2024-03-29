package eting.domain;

import eting.domain.pk.StoryPK;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifenjoy51 on 14. 12. 15.
 */
@Data
@Entity
@IdClass(StoryPK.class)
@Table(name = "story")
public class Story {

    @Id
    @Column(name="incognito_id")
    private long incognitoId;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date storyDt;

    private String storyContent;

    private String storyType;

    @ManyToOne (fetch=FetchType.EAGER) //lazy fetch problems... fetch=FetchType.LAZY,
    @JoinColumn(name="incognito_id", insertable = false, updatable = false)
    private Incognito incognito;

    /**
     * public constructor.
     */
    public Story(){};

    public Story(Incognito incognito){
        this.incognito = incognito;
    }


}
