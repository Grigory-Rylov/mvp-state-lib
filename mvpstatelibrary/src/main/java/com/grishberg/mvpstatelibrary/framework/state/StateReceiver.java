package com.grishberg.mvpstatelibrary.framework.state;

/**
 * Created by grishberg on 24.01.17.
 */
@FunctionalInterface
public interface StateReceiver<S extends MvpState> {
    void updateState(S presenterState);
}
