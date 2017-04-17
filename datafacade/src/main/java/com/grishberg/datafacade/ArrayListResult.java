package com.github.datafacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishberg on 15.01.17.
 */
public class ArrayListResult<T> extends ArrayList<T> implements ListResultCloseable<T> {

    private boolean isClosed;

    public ArrayListResult(final List<T> list) {
        super(list);
    }

    public static <T> ListResultCloseable<T> fromList(final List<T> source) {
        return new ArrayListResult<>(source);
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }
}
