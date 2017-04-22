package com.github.mvpstatelibexample.mvp.models.second;

import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.state.StateReceiver;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Locale;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 23.01.17.
 */
public class SecondModel {
    public static final int TIMEOUT = 500;

    public void requestData(final StateReceiver<MvpState> callback) {
        final Observable<SecondPresenterState.ResponseState> screenValuesObservable = Observable.create(subscriber -> {
            // emulating long routine
            final ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(String.format(Locale.US, "item %d", i));
                ThreadUtils.pause(TIMEOUT);
            }
            subscriber.onNext(new SecondPresenterState.ResponseState(list));
            subscriber.onCompleted();
        });

        screenValuesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::updateState);
    }
}
