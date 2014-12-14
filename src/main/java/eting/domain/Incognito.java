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
    private int incognitoId;

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
    private User user;


}
