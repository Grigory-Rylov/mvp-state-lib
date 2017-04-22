package com.github.mvpstatelib.framework.state;

/**
 * Created by grishberg on 25.01.17.
 */
public interface StateObserver<T extends MvpState> {
    void onStateUpdated(T model);
}
