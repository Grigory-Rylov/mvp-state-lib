package com.github.mvpstatelibexample.mvp.presenters;

import com.github.mvpstatelibexample.mvp.models.SecondModel;
import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState.RequestState;
import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState.ResponseState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState.ProgressState;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondScreenPresenter extends BaseMvpPresenter<SecondViewState> {
    private SecondModel model;

    public SecondScreenPresenter() {
        model = new SecondModel();
    }

    @Override
    protected void onStateUpdated(final MvpState state) {
        if (state instanceof RequestState) {
            requestDataFromModel();
        } else if (state instanceof ResponseState) {
            processResponse((ResponseState) state);
        }
    }

    /**
     * process response from model
     *
     * @param responseState
     */
    private void processResponse(final ResponseState responseState) {
        if (responseState.getValues() == null) {
            requestDataFromModel();
            return;
        }
        updateViewState(new ProgressState(false));
        updateViewState(new SecondViewState.NewValuesState(responseState.getValues()));
    }

    private void requestDataFromModel() {
        updateViewState(new ProgressState(true));
        model.requestData(this);
    }

    @Override
    protected void onNonSerializableEmpty(SecondViewState secondViewState) {
        if (secondViewState instanceof SecondViewState.NewValuesState) {
            requestDataFromModel();
        }
    }
}
