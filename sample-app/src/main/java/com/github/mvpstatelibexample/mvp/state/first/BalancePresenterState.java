package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.AbsMvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public abstract class BalancePresenterState extends AbsMvpState {
    public static class RequestState extends BalancePresenterState {
    }

    public static class ResponseState extends BalancePresenterState {
    }
}
