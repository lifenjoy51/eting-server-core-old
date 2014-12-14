package eting.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private long userId;

    @Column(nullable = false)
    private String userPw;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDt;

    @Column(nullable = false)
    private String pushKey;

    @Column(nullable = false)
    private String os;

    @Column
    private String activeYn;
}
