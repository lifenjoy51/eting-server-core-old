package eting.util;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by lifenjoy51 on 14. 12. 6.
 */
public class Util {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(), APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static Date getDt(){
        Date now = new Date();
        long timestamp = now.getTime();
        return new Date((timestamp/1000)*1000); //milisecond를 제거한다.
    }

    public static String getDtStr(){
        Date dt = new Date();
        String currentTime = getDtFormat().format(dt);
        return currentTime;
    }

    public static SimpleDateFormat getDtFormat(){
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf;
    }


    public static void main(String[] args){
    }

}
