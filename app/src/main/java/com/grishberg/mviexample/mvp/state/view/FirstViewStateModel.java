package com.grishberg.mviexample.mvp.state.view;

import com.grishberg.mviexample.framework.MvpState;

/**
 * Created by grishberg on 23.01.17.
 * Sample view state one
 */
public class FirstViewStateModel implements MvpState {
    private String title;
    private String description;
    private int count;
    private boolean isError;
    private boolean isProgress;

    public FirstViewStateModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public FirstViewStateModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public FirstViewStateModel setCount(int count) {
        this.count = count;
        return this;
    }

    public FirstViewStateModel setError(boolean error) {
        isError = error;
        return this;
    }

    public FirstViewStateModel setProgress(boolean progress) {
        isProgress = progress;
        return this;
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

    public boolean isError() {
        return isError;
    }

    public boolean isProgress() {
        return isProgress;
    }
}
