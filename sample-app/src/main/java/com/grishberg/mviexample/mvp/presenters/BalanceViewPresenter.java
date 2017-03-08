package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.balance.BalancePresenterState;
import com.grishberg.mviexample.mvp.state.balance.BalanceViewState;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalanceViewPresenter extends BaseMvpPresenter<BalanceViewState> {

    @Override
    protected void onStateUpdated(MvpState state) {
        if (state instanceof BalancePresenterState.RequestState) {
            updateViewState(new BalanceViewState.UpdateBalanceState("1000 $"));
        }
    }
}
