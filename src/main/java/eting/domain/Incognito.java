package eting.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by lifenjoy51 on 14. 12. 13.
 */
@Data
@Entity
@Table(name = "incognito")
public class Incognito {

    @Id
    @Column(name="incognito_id")
    private long incognitoId;

    @Column
    private String etingGroup;

    @Column
    private String etingType;

    @Column
    private String gender;

    @Column
    private int birthYear;

    @Column
    private int etingPoint;

    @Column
    private String openYn;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="incognito_id")
    private Device device;

    public Incognito(){};

    public Incognito(Device device){
        this.setIncognitoId(device.getDeviceId());
    };
}
