package com.grishberg.mviexample.mvp.state.presenter;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalancePresenterState implements MvpState {

    public enum State {
        REQUEST,
        RESPONSE
    }

    private State state;

    public static BalancePresenterState makeResponse() {
        BalancePresenterState state = new BalancePresenterState();
        state.state = State.RESPONSE;
        return state;
    }

    public static BalancePresenterState makeRequest() {
        BalancePresenterState state = new BalancePresenterState();
        state.state = State.REQUEST;
        return state;
    }

    public State getState() {
        return state;
    }
}
