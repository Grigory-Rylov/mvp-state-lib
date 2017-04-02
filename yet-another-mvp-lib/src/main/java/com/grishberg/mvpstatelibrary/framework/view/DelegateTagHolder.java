package com.grishberg.mvpstatelibrary.framework.view;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;

/**
 * Created by grishberg on 26.01.17.
 */

public interface DelegateTagHolder<T extends MvpState> extends StateObserver<T> {
    int getId();
}