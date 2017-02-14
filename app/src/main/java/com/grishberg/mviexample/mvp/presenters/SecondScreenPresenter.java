package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mviexample.mvp.models.SecondModel;
import com.grishberg.mviexample.mvp.state.presenter.SecondPresenterStateModel;
import com.grishberg.mviexample.mvp.state.view.SecondViewStateModel;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondScreenPresenter extends BaseMvpPresenter<SecondViewStateModel> {
    private SecondModel model;

    public SecondScreenPresenter() {
        model = new SecondModel();
    }

    @Override
    protected void onStateUpdated(final MvpState presenterState) {
        if (presenterState instanceof SecondPresenterStateModel) {
            switch (((SecondPresenterStateModel) presenterState).getState()) {
                case BUTTON_CLICKED:
                    requestDataFromModel();
                    break;
                case RESPONSE_RECEIVED:
                    processResponse((SecondPresenterStateModel) presenterState);
                    break;
            }
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
        model.requestData(this);
    }

    @Override
    protected void onNonSerializableEmpty(final SecondViewStateModel viewState) {
        requestDataFromModel();
    }
}
