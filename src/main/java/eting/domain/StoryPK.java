package eting.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lifenjoy51 on 14. 12. 15.
 */
@Data
public class StoryPK implements Serializable {

    @Column(name = "incognito_id")
    private long incognitoId;

    private Date storyDt;

    public StoryPK(){};

    public StoryPK(long incognitoId, Date storyDt){
        this.incognitoId = incognitoId;
        this.storyDt = storyDt;
    };
}
