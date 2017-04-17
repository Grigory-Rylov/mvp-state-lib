package com.github.mvpstatelib.framework.state;

/**
 * Created by grishberg on 23.01.17.
 */

public interface ModelWithNonSerializable<T> extends MvpState {
    boolean isNonSerializableNull();

    void setNonSerializableValue(T value);
}
