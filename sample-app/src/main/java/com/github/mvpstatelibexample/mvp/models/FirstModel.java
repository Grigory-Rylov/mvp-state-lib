package com.github.mvpstatelibexample.mvp.models;

import com.github.mvpstatelibexample.mvp.state.first.FirstPresenterStateModel.ResponseState;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 23.01.17.
 */
@SuppressWarnings("unchecked")
public class FirstModel {
    public static final int TIMEOUT = 5000;

    public void getData(final StateReceiver presenter) {
        // извлечь данные из сети
        final Observable<ResponseState> screenValuesObservable = Observable.create(subscriber -> {
            ThreadUtils.pause(TIMEOUT);
            subscriber.onNext(new ResponseState("title 1", "description", 777));
            subscriber.onCompleted();
        });
        screenValuesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::updateState);
    }
}
