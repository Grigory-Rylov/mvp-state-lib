package com.github.mvpstatelibexample.utils;

import android.util.Log;

/**
 * Created by grishberg on 23.04.17.
 */

public class LoggerCat implements Logger {
    @Override
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }
}
