package com.grishberg.mviexample.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMviPresenter<V extends BaseView<S>, S extends Serializable, PS extends Serializable> {
    public static final String VIEW_STATE_SUFFIX = ":VIEW_STATE";
    public static final String PRESENTER_STATE_SUFFIX = ":PRESENTER_STATE";
    private V view;
    private S viewState;
    private PS presenterState;

    protected void updateViewState(S viewState) {
        this.viewState = viewState;
        if (view != null) {
            view.updateView(viewState);
        }
    }

    public void attachView(final V view, @Nullable final Bundle savedInstanceState) {
        this.view = view;
        if (viewState == null) {
            viewState = restoreViewState(savedInstanceState);
        }
        if (presenterState == null) {
            presenterState = restorePresenterState(savedInstanceState);
        }
        if (viewState != null) {
            view.updateView(viewState);
        }
    }

    private S restoreViewState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (S) savedInstanceState.getSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX);
    }

    private PS restorePresenterState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (PS) savedInstanceState.getSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX);
    }

    public void detachView() {
        view = null;
    }

    public void saveInstanceState(final Bundle savedInstanceState) {
        if (viewState != null) {
            savedInstanceState.putSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX, viewState);
            savedInstanceState.putSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX, presenterState);
        }
    }

    public void updateState(PS presenterState) {
        this.presenterState = presenterState;
    }
}
