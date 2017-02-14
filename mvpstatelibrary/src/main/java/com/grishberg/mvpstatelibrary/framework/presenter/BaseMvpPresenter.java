package com.grishberg.mvpstatelibrary.framework.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.grishberg.mvpstatelibrary.framework.state.ModelWithNonSerializable;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;
import com.grishberg.mvpstatelibrary.framework.state.StateReceiver;

import java.util.HashSet;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMvpPresenter<VS extends MvpState>
        implements StateReceiver<MvpState> {
    private static final String VIEW_STATE_SUFFIX = ":VIEW_STATE";
    private static final String PRESENTER_STATE_SUFFIX = ":PRESENTER_STATE";
    final HashSet<StateObserver<VS>> observers = new HashSet<>();

    private VS viewState;
    private MvpState presenterState;

    protected void updateViewState(VS viewState) {
        this.viewState = viewState;

        notifyObservers();
    }

    private void notifyObservers() {
        for (StateObserver<VS> observer : observers) {
            observer.onModelUpdated(viewState);
        }
    }

    public void subscribe(final StateObserver<VS> stateObserver) {
        if (observers.add(stateObserver) && viewState != null) {
            if (viewState instanceof ModelWithNonSerializable &&
                    ((ModelWithNonSerializable) viewState).isNonSerializableNull()) {
                onNonSerializableEmpty(viewState);
                return;
            }
            stateObserver.onModelUpdated(viewState);
        }
    }

    public void unSubscribe(final StateObserver<VS> stateObserver) {
        observers.remove(stateObserver);
    }

    public void restoreState(@Nullable Bundle savedInstanceState) {
        if (viewState == null) {
            viewState = restoreViewState(savedInstanceState);
        }
        if (presenterState == null) {
            final MvpState restoredState = restorePresenterState(savedInstanceState);
            if (restoredState != null) {
                updateState(restoredState);
            }
        }
    }

    protected void onNonSerializableEmpty(VS viewState) {
        //To be overriden in subclass
    }

    private VS restoreViewState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (VS) savedInstanceState.getSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX);
    }

    private MvpState restorePresenterState(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return null;
        }
        return (MvpState) savedInstanceState.getSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX);
    }

    public void saveInstanceState(final Bundle savedInstanceState) {
        if (viewState != null) {
            savedInstanceState.putSerializable(this.getClass().getName() + VIEW_STATE_SUFFIX, viewState);
            savedInstanceState.putSerializable(this.getClass().getName() + PRESENTER_STATE_SUFFIX, presenterState);
        }
    }

    @Override
    public void updateState(final MvpState presenterState) {
        this.presenterState = presenterState;
        onStateUpdated(this.presenterState);
    }

    protected abstract void onStateUpdated(MvpState presenterState);
}
