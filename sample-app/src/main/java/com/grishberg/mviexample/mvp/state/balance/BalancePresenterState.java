package com.grishberg.mviexample.mvp.state.balance;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public interface BalancePresenterState extends MvpState {
    class RequestState implements BalancePresenterState {
    }

    class ResponseState implements BalancePresenterState {
    }
}
