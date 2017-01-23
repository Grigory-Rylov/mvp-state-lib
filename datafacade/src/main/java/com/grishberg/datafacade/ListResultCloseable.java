package com.grishberg.datafacade;

import java.io.Closeable;
import java.util.List;

/**
 * Created by grishberg on 31.12.16.
 * Интерфейс коллекции, который имеет метод закрытия
 */

public interface ListResultCloseable<T> extends List<T>, Closeable {
    boolean isClosed();
}
