package com.grishberg.mviexample.mvp.state.presenter;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstPresenterStateModel implements MvpState {
    public enum State {
        BUTTON_CLICKED
    }

    private State currentState;

    public static FirstPresenterStateModel makeClick() {
        FirstPresenterStateModel state = new FirstPresenterStateModel();
        state.currentState = State.BUTTON_CLICKED;
        return state;
    }

    public State getCurrentState() {
        return currentState;
    }
}
