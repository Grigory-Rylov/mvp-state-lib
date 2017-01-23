package com.grishberg.mviexample.framework.model;

import com.grishberg.mviexample.framework.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */

public interface ModelWithNonSerializable<T> extends MvpState {
    boolean isNonSerializableNull();

    void setNonSerializableValue(T value);
}
