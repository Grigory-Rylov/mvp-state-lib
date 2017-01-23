package com.grishberg.mviexample.utils;

/**
 * Created by grishberg on 23.01.17.
 */
public class ThreadUtils {
    public static void pause(final long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
