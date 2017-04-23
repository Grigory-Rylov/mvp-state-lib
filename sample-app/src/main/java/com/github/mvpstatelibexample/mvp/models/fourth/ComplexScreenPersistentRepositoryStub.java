package com.github.mvpstatelibexample.mvp.models.fourth;

import android.util.Log;

import com.github.mvpstatelibexample.mvp.models.beans.fourth.PersistentModel;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexScreenPersistentRepositoryStub implements ComplexScreenPersistentRepository {
    private static final String TAG = ComplexScreenPersistentRepositoryStub.class.getSimpleName();

    @Override
    public Observable<List<PersistentModel>> getPersistentData() {
        Log.d(TAG, "getPersistentData: getPersistentData");
        return Observable.create(subscriber -> {
            ThreadUtils.pause(3000);
            ArrayList<PersistentModel> modelArrayList = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                modelArrayList.add(new PersistentModel(String.format(Locale.US, "name %d", i)));
            }
            subscriber.onNext(modelArrayList);
            subscriber.onCompleted();
            Log.d(TAG, "getPersistentData: onCompleted");
        });
    }
}
