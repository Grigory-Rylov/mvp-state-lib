package com.github.mvpstatelibexample.mvp.presenters.fourh;

import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexScreenInteractor;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskViewState.*;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexTaskPresenter extends BaseMvpPresenter {
    private final ComplexScreenInteractor interactor;

    public ComplexTaskPresenter(ComplexScreenInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onStateUpdated(MvpState presenterState) {
        GeneratedComplexTaskPresenterSubscriber.processState(this, presenterState);
    }

    @SubscribeState
    void onScreenStarted(ComplexTaskPresenterState.StartScreenState state) {
        interactor.requestPersistenDataFromRepository(this);
    }

    @SubscribeState
    void onDataReceivedFromServer(ComplexTaskPresenterState.PersistentStoreResponse state) {
        updateViewState(new ComplexTaskDataDownloadingState(state.getModelList().size()));
    }

    @SubscribeState
    void onConfirmedStepTwo(ComplexTaskPresenterState.ConfirmSecondStepState state) {
        interactor.startStepTwo(this);
    }

    @SubscribeState
    void onUpdatedStepTwo(ComplexTaskPresenterState.UpdateSecondStepState state) {
        updateViewState(new UpdateSecondStepState(state.getModelListLeft(), state.getProcessedCount()));
    }

    @SubscribeState
    void onSecondStepCompleted(ComplexTaskPresenterState.CompletedSecondStepState state) {
        updateViewState(new CompleteSecondStepState());
    }
}
