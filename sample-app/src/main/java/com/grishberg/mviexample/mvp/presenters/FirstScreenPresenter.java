package com.grishberg.mviexample.mvp.presenters;

import com.grishberg.mviexample.mvp.state.model.FirstModelState;
import com.grishberg.mviexample.mvp.state.presenter.FirstPresenterStateModel;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mviexample.mvp.models.FirstModel;
import com.grishberg.mviexample.mvp.state.view.FirstViewStateModel;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstScreenPresenter extends BaseMvpPresenter<FirstViewStateModel> {
    private FirstModel model;

    public FirstScreenPresenter() {
        model = new FirstModel();
        model.setPresenter();
    }

    /**
     * События от модели и вью
     *
     * @param presenterState событие
     */
    @Override
    protected void onStateUpdated(final MvpState presenterState) {
        if (presenterState instanceof FirstModelState) {
            // ответ от модели
            processResponse((FirstModelState) presenterState);
            return;
        }
        if (presenterState instanceof FirstPresenterStateModel) {
            switch (((FirstPresenterStateModel) presenterState).getCurrentState()) {
                case BUTTON_CLICKED:
                    updateViewState(new FirstViewStateModel().setProgress(true));
                    model.getData(this);
                    break;
            }
        }
    }

    /**
     * Process response form model
     *
     * @param presenterState
     */
    private void processResponse(final FirstModelState presenterState) {
        updateViewState(new FirstViewStateModel()
                .setProgress(false)
                .setTitle(presenterState.getTitle())
                .setDescription(presenterState.getDescription())
                .setCount(presenterState.getCount()));
    }
}
