package com.github.mvpstatelib.framework.state;

import java.io.Serializable;

/**
 * Created by grishberg on 22.04.17.
 */

public interface MvpState extends Serializable {
    void beforeStateReceived();

    void afterStateReceived();

    boolean isNeedToSaveState();
}
