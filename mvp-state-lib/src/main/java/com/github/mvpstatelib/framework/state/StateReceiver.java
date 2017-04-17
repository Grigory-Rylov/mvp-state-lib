package com.github.mvpstatelib.framework.state;

/**
 * Created by grishberg on 24.01.17.
 */
public interface StateReceiver<T extends MvpState> {
    void updateState(T presenterState);
}
