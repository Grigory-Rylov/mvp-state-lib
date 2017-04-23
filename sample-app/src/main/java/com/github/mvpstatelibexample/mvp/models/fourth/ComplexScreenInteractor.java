package com.github.mvpstatelibexample.mvp.models.fourth;

import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState.*;
import com.github.mvpstatelibexample.utils.Logger;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexScreenInteractor {
    private static final String TAG = ComplexScreenInteractor.class.getSimpleName();

    private final ComplexScreenPersistentRepository persistentRepository;
    private final ComplexTaskApiService apiService;
    private final ComplexTaskConvertedRepository convertedRepository;
    private final Logger log;
    private boolean isSecondStepInProgress;

    public ComplexScreenInteractor(ComplexScreenPersistentRepository persistentRepository,
                                   ComplexTaskApiService apiService,
                                   ComplexTaskConvertedRepository convertedRepository, Logger log) {
        this.persistentRepository = persistentRepository;
        this.apiService = apiService;
        this.convertedRepository = convertedRepository;
        this.log = log;
    }

    public void requestPersistenDataFromRepository(StateReceiver<MvpState> callback) {
        persistentRepository.getPersistentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> apiService.requestPersistentDataCalculation(response))
                .flatMap(apiResponse -> Observable.just(new PersistentStoreResponse(apiResponse)))
                .subscribe(callback::updateState);
    }

    public void startStepTwo(StateReceiver<MvpState> callback) {
        final AtomicInteger elementsCountAtomic = new AtomicInteger();
        isSecondStepInProgress = true;
        convertedRepository.requestCalculatedData()
                .flatMap(items -> {
                    log.d(TAG, "requestCalculatedData flatMap " + items.size());
                    elementsCountAtomic.set(items.size());
                    return processElements(items);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(leftItemList -> {
                            int processedCount = elementsCountAtomic.get() - leftItemList.size();
                            log.d(TAG, "startStepTwo: " + processedCount);
                            callback.updateState(new UpdateSecondStepState(leftItemList, processedCount));
                        },
                        throwable -> {
                            callback.updateState(new UpdateSecondStepNetworkError());
                            isSecondStepInProgress = false;
                        },
                        () -> {
                            log.d(TAG, "startStepTwo: CompletedSecondStepState");
                            callback.updateState(new CompletedSecondStepState());
                            isSecondStepInProgress = false;
                        }
                );
    }

    private Observable<List<ApiConvertedModel>> processElements(final List<ApiConvertedModel> items) {
        return Observable.create(subscriber -> {
            ArrayList<ApiConvertedModel> elementsLeft = new ArrayList<>(items);
            Iterator<ApiConvertedModel> iterator = elementsLeft.iterator();
            while (iterator.hasNext()) {
                ApiConvertedModel currentModel = iterator.next();
                ThreadUtils.pause(2000);
                iterator.remove();
                log.d(TAG, "processElements: " + elementsLeft.size());
                subscriber.onNext(elementsLeft);
            }
            log.d(TAG, "processElements: onCompleted " + elementsLeft.size());
            subscriber.onCompleted();
        });
    }

    public boolean isSecondStepInProgress() {
        return isSecondStepInProgress;
    }
}
