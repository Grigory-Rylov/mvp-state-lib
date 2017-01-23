package com.grishberg.mviexample.mvp.models;

import com.grishberg.mviexample.framework.StateReceiver;
import com.grishberg.mviexample.mvp.state.presenter.FirstPresenterStateModel;
import com.grishberg.mviexample.utils.ThreadUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 23.01.17.
 */
public class FirstModel {
    public static final int TIMEOUT = 5000;
    private StateReceiver<FirstPresenterStateModel> presenter;

    public void setPresenter(final StateReceiver<FirstPresenterStateModel> presenter) {
        this.presenter = presenter;
    }

    public void getData() {
        // извлечь данные из сети
        final Observable<FirstPresenterStateModel> screenValuesObservable = Observable.create(subscriber -> {
            ThreadUtils.pause(TIMEOUT);
            subscriber.onNext(FirstPresenterStateModel
                    .makeResponse("title 1", "desctiption", 777));
            subscriber.onCompleted();
        });
        screenValuesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> presenter.updateState(response));
    }
}
