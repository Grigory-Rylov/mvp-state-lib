package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.framework.BaseMviPresenter;
import com.grishberg.mviexample.mvp.models.SecondModel;
import com.grishberg.mviexample.mvp.state.presenter.SecondPresenterStateModel;
import com.grishberg.mviexample.mvp.state.view.SecondViewStateModel;
import com.grishberg.mviexample.mvp.view.SecondView;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondScreenPresenter extends BaseMviPresenter<SecondView, SecondViewStateModel, SecondPresenterStateModel> {
    private SecondModel model;

    public SecondScreenPresenter() {
        model = new SecondModel();
        model.setPresenter(this);
    }

    @Override
    protected void onStateUpdated(SecondPresenterStateModel presenterState) {
        switch (presenterState.getState()) {
            case BUTTON_CLICKED:
                requestDataFromModel();
                break;
            case RESPONSE_RECEIVED:
                processResponse(presenterState);
                break;
        }
    }

    /**
     * process response from model
     *
     * @param presenterState
     */
    private void processResponse(final SecondPresenterStateModel presenterState) {
        if (presenterState.getValues() == null) {
            requestDataFromModel();
            return;
        }
        updateViewState(new SecondViewStateModel()
                .setProgress(false)
                .setValues(presenterState.getValues()));
    }

    private void requestDataFromModel() {
        updateViewState(new SecondViewStateModel().setProgress(true));
        model.requestData();
    }

    @Override
    protected void onNonSerializableEmpty(final SecondViewStateModel viewState) {
        requestDataFromModel();
    }
}
