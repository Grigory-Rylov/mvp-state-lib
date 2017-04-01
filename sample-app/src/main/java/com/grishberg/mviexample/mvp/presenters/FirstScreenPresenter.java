package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.models.FirstModel;
import com.grishberg.mviexample.mvp.state.first.FirstPresenterStateModel.RequestState;
import com.grishberg.mviexample.mvp.state.first.FirstPresenterStateModel.ResponseState;
import com.grishberg.mviexample.mvp.state.first.FirstViewStateModel;
import com.grishberg.mviexample.mvp.state.first.FirstViewStateModel.ProgressState;
import com.grishberg.mviexample.mvp.state.first.FirstViewStateModel.SuccessState;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstScreenPresenter extends BaseMvpPresenter<FirstViewStateModel> {
    private FirstModel model;

    public FirstScreenPresenter() {
        model = new FirstModel();
    }

    /**
     * События от модели и вью
     *
     * @param state событие
     */
    @Override
    protected void onStateUpdated(final MvpState state) {
        if (state instanceof ResponseState) {
            processResponse((ResponseState) state);
        } else if (state instanceof RequestState) {
            processRequest();
        }
    }

    private void processRequest() {
        updateViewState(new ProgressState(true));
        model.getData(this);
    }

    /**
     * Process response form model
     *
     * @param state
     */
    private void processResponse(final ResponseState state) {
        updateViewState(new ProgressState(false));
        updateViewState(new SuccessState(state.getTitle(),
                state.getDescription(),
                state.getCount()));
    }
}
