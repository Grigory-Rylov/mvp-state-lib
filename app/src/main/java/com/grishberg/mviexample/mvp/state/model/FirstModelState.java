package com.grishberg.mviexample.mvp.state.model;

import com.grishberg.mviexample.mvp.state.presenter.FirstPresenterStateModel;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 14.02.17.
 */
public class FirstModelState implements MvpState {
    private String title;
    private String description;
    private int count;

    public static FirstModelState makeResponse(final String title,
                                               final String description,
                                               final int count) {
        FirstModelState state = new FirstModelState();
        state.title = title;
        state.description = description;
        state.count = count;
        return state;
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
