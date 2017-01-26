package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.presenter.BalancePresenterState;
import com.grishberg.mviexample.mvp.state.view.BalanceViewState;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalanceViewPresenter extends BaseMvpPresenter<BalanceViewState, BalancePresenterState> {
    private static final String TAG = BalanceViewPresenter.class.getSimpleName();

    @Override
    protected void onStateUpdated(BalancePresenterState presenterState) {
        if (presenterState.getState() == BalancePresenterState.State.REQUEST) {
            updateViewState(new BalanceViewState().setBalance("1000 $"));
        }
    }
}
