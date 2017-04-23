package com.github.mvpstatelibexample.mvp.models.fourth;

import android.util.Log;

import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Created by grishberg on 23.04.17.
 */

public class ComplexTaskConvertedRepositoryStub implements ComplexTaskConvertedRepository {
    private static final String TAG = ComplexTaskConvertedRepositoryStub.class.getSimpleName();
    private static final int TIMEOUT = 3000;

    @Override
    public Observable<List<ApiConvertedModel>> requestCalculatedData() {
        return Observable.create(subscriber -> {
            Log.d(TAG, "requestPersistentDataCalculation: request");
            ThreadUtils.pause(TIMEOUT);
            ArrayList<ApiConvertedModel> modelArrayList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                modelArrayList.add(new ApiConvertedModel(String.format(Locale.US, "api model name %d", i)));
            }
            subscriber.onNext(modelArrayList);
            Log.d(TAG, "requestPersistentDataCalculation: onCompleted");
            subscriber.onCompleted();
        });
    }
}
