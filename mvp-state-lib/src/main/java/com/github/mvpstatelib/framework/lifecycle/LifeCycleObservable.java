package com.github.mvpstatelib.framework.lifecycle;

/**
 * Created by grishberg on 26.01.17.
 */

public interface LifeCycleObservable {
    void registerObserver(LifeCycleObserver observer);

    void unRegisterObserver(LifeCycleObserver observer);

    boolean isFinishing();

    String getDelegateTag();
}
