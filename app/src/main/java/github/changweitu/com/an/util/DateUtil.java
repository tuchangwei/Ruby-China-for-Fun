package github.changweitu.com.an.util;

import java.util.Date;

/**
 * Created by vale on 7/27/16.
 */

public final class DateUtil {
    public static String before(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        long seconds = diff/1000;
        long minutes = seconds/60;
        long hours = minutes/60;
        long days = hours/24;
        if (days>0) {
            return days + " 天前";
        } else if (hours > 0) {
            return hours + " 小时前";
        } else if (minutes > 0) {
            return minutes + " 分钟前";
        } else if (seconds > 0 && seconds < 10) {
            return "几秒前";
        } else {
            return "刚刚";
        }
    }
}
