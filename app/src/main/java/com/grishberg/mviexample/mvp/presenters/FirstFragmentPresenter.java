package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.presenter.FirstFragmentPresenterState;
import com.grishberg.mviexample.mvp.state.view.FirstFragmentViewState;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 24.01.17.
 */
public class FirstFragmentPresenter extends BaseMvpPresenter<FirstFragmentViewState, FirstFragmentPresenterState> {

    @Override
    protected void onStateUpdated(final FirstFragmentPresenterState presenterState) {

    }
}
