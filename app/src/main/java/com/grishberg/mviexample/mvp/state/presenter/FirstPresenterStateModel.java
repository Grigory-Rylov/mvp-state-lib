package com.grishberg.mviexample.mvp.state.presenter;

import java.io.Serializable;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstPresenterStateModel implements Serializable {
    public enum State {
        CLICKED,
        RESPONSE_RECEIVED
    }

    private State currentState;
    private String title;
    private String description;
    private int count;

    public static FirstPresenterStateModel makeClick() {
        FirstPresenterStateModel state = new FirstPresenterStateModel();
        state.currentState = State.CLICKED;
        return state;
    }

    public static FirstPresenterStateModel makeResponse(final String title,
                                                        final String description,
                                                        final int count) {
        FirstPresenterStateModel state = new FirstPresenterStateModel();
        state.currentState = State.RESPONSE_RECEIVED;
        state.title = title;
        state.description = description;
        state.count = count;
        return state;
    }

    public State getCurrentState() {
        return currentState;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }
}
