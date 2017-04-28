package com.github.mvpstatelibexample.mvp.presenters.fourh;

import com.github.mvpstatelib.framework.state.StateObserver;
import com.github.mvpstatelib.framework.state.ViewState;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.mvp.models.fourth.ComplexScreenInteractor;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState.CompletedSecondStepState;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState.PersistentStoreResponse;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskViewState;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskViewState.ComplexTaskDataDownloadingState;
import com.github.mvpstatelibexample.utils.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by grishberg on 23.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComplexTaskPresenterTest {
    public static final int MODEL_LIST_SIZE = 1000;
    @Mock
    StateObserver<ViewState> view;
    @Mock
    Logger log;

    @Mock
    ComplexScreenInteractor interactor;

    ComplexTaskPresenter presenter;

    @Mock
    List<ApiConvertedModel> modelList;

    @Before
    public void setUp() throws Exception {
        when(modelList.size()).thenReturn(MODEL_LIST_SIZE);
        presenter = new ComplexTaskPresenter(interactor, log);
        presenter.subscribe(view);
    }

    @Test
    public void testWhenScreenStarted() {
        presenter.updateState(new ComplexTaskPresenterState.StartScreenState());
        verify(interactor).requestPersistenDataFromRepository(presenter);
    }

    @Test
    public void testWhenInteractorReceivedFirstStepData() {
        PersistentStoreResponse state = mock(PersistentStoreResponse.class);
        when(state.getModelList()).thenReturn(modelList);

        presenter.updateState(state);

        ArgumentCaptor<ComplexTaskDataDownloadingState> captor = ArgumentCaptor
                .forClass(ComplexTaskDataDownloadingState.class);
        verify(view).onStateUpdated(captor.capture());
        assertEquals(MODEL_LIST_SIZE, captor.getValue().getProcessedCount());
        assertEquals(true, captor.getValue().isShowRequest());
    }

    @Test
    public void testWhenCompletedStepTwo() {

        presenter.updateState(new CompletedSecondStepState());

        ArgumentCaptor<ComplexTaskViewState.CompleteSecondStepState> captor = ArgumentCaptor
                .forClass(ComplexTaskViewState.CompleteSecondStepState.class);
        verify(view).onStateUpdated(captor.capture());
    }
}