package eting.code;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
public enum Langs {

    KOREAN("KR")
    , ENGLISH("EN")
    , CHINESE("BN");

    private String lang;

    private Langs(String lang) {
        this.lang = lang;
    }

    public String get() {
        return lang;
    }
}
