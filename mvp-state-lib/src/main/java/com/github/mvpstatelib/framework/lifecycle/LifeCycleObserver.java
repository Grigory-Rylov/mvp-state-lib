package com.github.mvpstatelib.framework.lifecycle;

import android.os.Bundle;

/**
 * Created by grishberg on 26.01.17.
 */

public interface LifeCycleObserver {
    void onCreate(Bundle state);

    void onResume();

    void onPause();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);
}
