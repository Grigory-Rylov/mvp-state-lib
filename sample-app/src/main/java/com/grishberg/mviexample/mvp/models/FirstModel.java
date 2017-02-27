package com.grishberg.mviexample.mvp.models;

import com.grishberg.mviexample.mvp.state.model.FirstModelState;
import com.grishberg.mvpstatelibrary.framework.state.StateReceiver;
import com.grishberg.mviexample.utils.ThreadUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstModel {
    public static final int TIMEOUT = 5000;

    public void setPresenter() {
    }

    public void getData(final StateReceiver presenter) {
        // извлечь данные из сети
        final Observable<FirstModelState> screenValuesObservable = Observable.create(subscriber -> {
            ThreadUtils.pause(TIMEOUT);
            subscriber.onNext(FirstModelState.makeResponse("title 1", "desctiption", 777));
            subscriber.onCompleted();
        });
        screenValuesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> presenter.updateState(response));
    }
}
