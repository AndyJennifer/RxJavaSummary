package util;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 15:02
 * Description:
 */


public class ThreadUtil {

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    public static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
