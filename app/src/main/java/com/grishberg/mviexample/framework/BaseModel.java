package com.grishberg.mviexample.framework;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by grishberg on 23.01.17.
 * Base model
 */
public abstract class BaseModel<P extends BaseMviPresenter, PS extends Serializable> {
    @Nullable
    private P presenter;

    protected void updatePresenterState(final PS state) {
        if (presenter != null) {
            presenter.updateState(state);
        }
    }
}
