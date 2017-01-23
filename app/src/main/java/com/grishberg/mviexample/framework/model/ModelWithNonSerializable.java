package com.grishberg.mviexample.framework.model;

import java.io.Serializable;

/**
 * Created by grishberg on 23.01.17.
 */

public interface ModelWithNonSerializable<T> extends Serializable {
    boolean isNonSerializableNull();

    void setNonSerializableValue(T value);
}
