package com.github.mvpstatelibexample.mvp.presenters.second;

import com.github.mvpstatelib.framework.state.PresenterState;
import com.github.mvpstatelib.framework.state.ViewState;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.github.mvpstatelibexample.mvp.models.second.SecondModel;
import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState.RequestState;
import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState.ResponseState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState.ProgressState;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondScreenPresenter extends BaseMvpPresenter {
    private SecondModel model;

    public SecondScreenPresenter() {
        model = new SecondModel();
    }

    @Override
    protected void onStateUpdated(final PresenterState state) {
        GeneratedSecondScreenPresenterSubscriber.processState(this, state);
    }

    /**
     * process response from model
     *
     * @param responseState
     */
    @SubscribeState
    void processResponse(final ResponseState responseState) {
        if (responseState.getValues() == null) {
            requestDataFromModel(null);
            return;
        }
        updateViewState(new ProgressState(false));
        updateViewState(new SecondViewState.NewValuesState(responseState.getValues()));
    }

    @SubscribeState
    void requestDataFromModel(RequestState state) {
        updateViewState(new ProgressState(true));
        model.requestData(this);
    }

    @Override
    protected void onNonSerializableEmpty(ViewState secondViewState) {
        if (secondViewState instanceof SecondViewState.NewValuesState) {
            requestDataFromModel(null);
        }
    }
}
