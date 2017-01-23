package com.grishberg.mviexample.mvp.state.presenter;

import com.grishberg.datafacade.ListResultCloseable;

import java.io.Serializable;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondPresenterStateModel implements Serializable {
    public enum State {
        BUTTON_CLICKED,
        RESPONSE_RECEIVED
    }

    private State state;

    private transient ListResultCloseable<String> values;

    private SecondPresenterStateModel() {
    }

    public static SecondPresenterStateModel makeFromClick() {
        SecondPresenterStateModel state = new SecondPresenterStateModel();
        state.state = State.BUTTON_CLICKED;
        return state;
    }

    public static SecondPresenterStateModel makeFromResponse(final ListResultCloseable<String> values) {
        SecondPresenterStateModel state = new SecondPresenterStateModel();
        state.state = State.RESPONSE_RECEIVED;
        state.values = values;
        return state;
    }

    public State getState() {
        return state;
    }

    public ListResultCloseable<String> getValues() {
        return values;
    }
}
