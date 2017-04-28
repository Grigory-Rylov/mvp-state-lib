package com.github.mvpstatelib.framework.view;

import com.github.mvpstatelib.framework.state.StateObserver;
import com.github.mvpstatelib.framework.state.ViewState;

/**
 * Created by grishberg on 26.01.17.
 */

public interface DelegateTagHolder<T extends ViewState> extends StateObserver<T> {
    int getId();
}
