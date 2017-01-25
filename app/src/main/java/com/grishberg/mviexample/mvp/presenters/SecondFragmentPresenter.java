package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.presenter.SecondFragmentPresenterState;
import com.grishberg.mviexample.mvp.state.view.SecondFragmentViewState;
import com.grishberg.mviexample.mvp.view.SecondFragmentView;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 25.01.17.
 */
public class SecondFragmentPresenter extends BaseMvpPresenter<SecondFragmentView, SecondFragmentViewState, SecondFragmentPresenterState> {
    private static final String TAG = SecondFragmentPresenter.class.getSimpleName();

    @Override
    protected void onStateUpdated(SecondFragmentPresenterState presenterState) {
        
    }
}
