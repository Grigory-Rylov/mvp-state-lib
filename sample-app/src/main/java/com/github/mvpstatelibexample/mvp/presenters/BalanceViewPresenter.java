package com.github.mvpstatelibexample.mvp.presenters;

import com.github.mvpstatelibexample.mvp.state.balance.BalancePresenterState;
import com.github.mvpstatelibexample.mvp.state.balance.BalanceViewState;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalanceViewPresenter extends BaseMvpPresenter{

    @Override
    protected void onStateUpdated(MvpState state) {
        if (state instanceof BalancePresenterState.RequestState) {
            updateViewState(new BalanceViewState.UpdateBalanceState("1000 $"));
        }
    }
}
