package eting.domain;

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

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE) //lazy fetch problems...
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
