package eting.domain.pk;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lifenjoy51 on 14. 12. 15.
 */
public class ExchangePK implements Serializable {
    @Column(name = "story_incognito_id")
    private long storyIncognitoId;

    private Date storyDt;

    @Column(name = "incognito_id")
    private long incognitoId;
}
