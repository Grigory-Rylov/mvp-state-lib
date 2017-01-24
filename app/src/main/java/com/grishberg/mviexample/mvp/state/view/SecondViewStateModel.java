package com.grishberg.mviexample.mvp.state.view;

import com.grishberg.datafacade.ListResultCloseable;
import com.grishberg.mvpstatelibrary.framework.state.ModelWithNonSerializable;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondViewStateModel implements ModelWithNonSerializable<ListResultCloseable<String>> {
    private transient ListResultCloseable<String> values;
    private boolean isError;
    private boolean isProgress;

    @Override
    public boolean isNonSerializableNull() {
        return values == null;
    }

    @Override
    public void setNonSerializableValue(final ListResultCloseable<String> value) {
        this.values = value;
    }

    public SecondViewStateModel setValues(ListResultCloseable<String> values) {
        this.values = values;
        return this;
    }

    public SecondViewStateModel setError(boolean error) {
        isError = error;
        return this;
    }

    public SecondViewStateModel setProgress(boolean progress) {
        isProgress = progress;
        return this;
    }

    public ListResultCloseable<String> getValues() {
        return values;
    }

    public boolean isError() {
        return isError;
    }

    public boolean isProgress() {
        return isProgress;
    }
}
