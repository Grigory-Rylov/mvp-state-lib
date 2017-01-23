package com.grishberg.mvpstatelibrary.framework.model;

import com.grishberg.mvpstatelibrary.framework.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */

public interface ModelWithNonSerializable<T> extends MvpState {
    boolean isNonSerializableNull();

    void setNonSerializableValue(T value);
}
