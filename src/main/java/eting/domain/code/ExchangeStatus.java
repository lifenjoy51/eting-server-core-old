package eting.domain.code;

/**
 * Created by lifenjoy51 on 14. 12. 24.
 */
public enum ExchangeStatus {

    INSERT_QUEUE("IQ") // insert queue
    , GET_STORY("GS") // get story
    , PASS_STORY("PS") // pass story
    , REPORT_STORY("RS") // report story
    , SAVE_REPLY("SR") // save reply
    , DELETE_REPLY("DR") // delete reply
    , REPORT_REPLY("RR") // report reply
    , LIKE_REPLY("LR"); // like reply

    private String status;

    private ExchangeStatus(String status) {
        this.status = status;
    }

    public String get() {
        return status;
    }
}
