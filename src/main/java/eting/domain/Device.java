package eting.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@Data
@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long deviceId;

    @Column(nullable = false)
    private String uuid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDt;

    @Column(nullable = false)
    private String pushKey;

    @Column(nullable = false)
    private String os;

    @Column
    private String activeYn;
}
