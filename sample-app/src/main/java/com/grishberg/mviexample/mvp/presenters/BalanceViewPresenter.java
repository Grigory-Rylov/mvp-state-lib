package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.presenter.BalancePresenterState;
import com.grishberg.mviexample.mvp.state.view.BalanceViewState;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

import static com.grishberg.mviexample.mvp.state.presenter.BalancePresenterState.State.REQUEST;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalanceViewPresenter extends BaseMvpPresenter<BalanceViewState> {

    @Override
    protected void onStateUpdated(MvpState presenterState) {
        if (!(presenterState instanceof BalancePresenterState)) {
            return;
        }
        if (((BalancePresenterState) presenterState).getState() == REQUEST) {
            updateViewState(new BalanceViewState().setBalance("1000 $"));
        }
    }
}
