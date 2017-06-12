package com.github.mvpstatelib.framework.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mvpstatelib.framework.state.ModelWithNonSerializable;
import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.framework.state.StateObserver;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.github.mvpstatelib.framework.state.ViewState;

import java.util.HashSet;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMvpPresenter
        implements StateReceiver<PresenterState> {
    private static final String VIEW_STATE_SUFFIX = ":VIEW_STATE";
    private static final String PRESENTER_STATE_SUFFIX = ":PRESENTER_STATE";
    private final HashSet<StateObserver<ViewState>> viewObservers = new HashSet<>();

    @Nullable
    private ViewState viewState;

    @Nullable
    private PresenterState presenterState;

    protected void updateViewState(@Nullable ViewState viewState) {
        if (this.viewState != null) {
            this.viewState = this.viewState.reduct(viewState);
        } else {
            this.viewState = viewState;
        }

        notifyObservers();
    }

    private void notifyObservers() {
        if (viewState != null) {
            viewState.beforeStateReceived();
        }
        for (StateObserver<ViewState> observer : viewObservers) {
            observer.onStateUpdated(viewState);
        }
        if (viewState != null) {
            viewState.afterStateReceived();
        }
    }

    public void subscribe(final StateObserver<ViewState> stateObserver) {
        if (viewObservers.add(stateObserver) && viewState != null) {
            if (viewState instanceof ModelWithNonSerializable &&
                    ((ModelWithNonSerializable) viewState).isNonSerializableNull()) {
                onNonSerializableEmpty(viewState);
                return;
            }
            stateObserver.onStateUpdated(viewState);
        }
        onSubscribed();
    }

    protected void onSubscribed() {
        //to be overridden in subclass
    }

    public void unSubscribe(final StateObserver<ViewState> stateObserver) {
        viewObservers.remove(stateObserver);
        onUnsubscribed();
    }

    protected void onUnsubscribed() {
        //to be overridden in subclass
    }

    public void restoreState(@Nullable Bundle savedInstanceState) {
        if (viewState == null) {
            viewState = restoreViewState(savedInstanceState);
        }
        if (presenterState == null) {
            final PresenterState restoredState = restorePresenterState(savedInstanceState);
            if (restoredState != null) {
                updateState(restoredState);
            }
        }
    }

    protected void onNonSerializableEmpty(ViewState viewState) {
        //To be overriden in subclass
    }

    private ViewState restoreViewState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (ViewState) savedInstanceState.getSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX);
    }

    private PresenterState restorePresenterState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (PresenterState) savedInstanceState.getSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX);
    }

    public void saveInstanceState(final Bundle savedInstanceState) {
        if (viewState != null) {
            savedInstanceState.putSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX, viewState);
            savedInstanceState.putSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX, presenterState);
        }
    }

    public void onDestroy() {
    }

    @Override
    public void updateState(final PresenterState presenterState) {
        if (presenterState != null && presenterState.isNeedToSaveState()) {
            if (this.presenterState != null) {
                this.presenterState = this.presenterState.reduct(presenterState);
            } else {
                this.presenterState = presenterState;
            }
        }
        onStateUpdated(presenterState);
    }

    protected void onStateUpdated(final PresenterState presenterState) {
        //to be overridden in subclass
    }
}
