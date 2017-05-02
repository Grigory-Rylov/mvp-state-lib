package com.github.mvpstatelib.framework.state;

/**
 * Created by grishberg on 28.04.17.
 */

public interface PresenterState extends MvpState {
    PresenterState reduct(PresenterState presenterState);
}
