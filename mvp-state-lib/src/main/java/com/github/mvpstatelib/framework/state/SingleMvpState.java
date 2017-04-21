package com.github.mvpstatelib.framework.state;

/**
 * Created by grishberg on 23.02.17.
 * State, that make default values after it receiving by View
 */

public interface SingleMvpState extends MvpState {
    void setDefaultState();
}
