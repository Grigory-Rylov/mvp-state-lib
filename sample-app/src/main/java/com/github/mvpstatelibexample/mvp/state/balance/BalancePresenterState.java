package com.github.mvpstatelibexample.mvp.state.balance;

import com.github.mvpstatelib.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public interface BalancePresenterState extends MvpState {
    class RequestState implements BalancePresenterState {
    }

    class ResponseState implements BalancePresenterState {
    }
}
