package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mviexample.mvp.models.FirstModel;
import com.grishberg.mviexample.mvp.state.presenter.FirstPresenterStateModel;
import com.grishberg.mviexample.mvp.state.view.FirstViewStateModel;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstScreenPresenter extends BaseMvpPresenter<FirstViewStateModel, FirstPresenterStateModel> {
    private FirstModel model;

    public FirstScreenPresenter() {
        model = new FirstModel();
        model.setPresenter(this);
    }

    @Override
    protected void onStateUpdated(final FirstPresenterStateModel presenterState) {
        switch (presenterState.getCurrentState()) {
            case CLICKED:
                updateViewState(new FirstViewStateModel().setProgress(true));
                model.getData();
                break;
            case RESPONSE_RECEIVED:
                processResponse(presenterState);
                break;
        }
    }

    private void processResponse(FirstPresenterStateModel presenterState) {
        updateViewState(new FirstViewStateModel()
                .setProgress(false)
                .setTitle(presenterState.getTitle())
                .setDescription(presenterState.getDescription())
                .setCount(presenterState.getCount()));
    }
}
