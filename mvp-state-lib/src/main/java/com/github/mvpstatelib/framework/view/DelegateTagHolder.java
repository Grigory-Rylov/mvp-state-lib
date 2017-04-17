package com.github.mvpstatelib.framework.view;

import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.state.StateObserver;

/**
 * Created by grishberg on 26.01.17.
 */

public interface DelegateTagHolder<T extends MvpState> extends StateObserver<T> {
    int getId();
}
