package com.github.mvpstatelibexample.mvp.models.fourth;

import android.util.Log;

import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.PersistentModel;
import com.github.mvpstatelibexample.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexTaskApiServiceStub implements ComplexTaskApiService {
    private static final String TAG = ComplexTaskApiServiceStub.class.getSimpleName();
    private static final int TIMEOUT = 3000;

    @Override
    public Observable<List<ApiConvertedModel>> requestPersistentDataCalculation(List<PersistentModel> response) {
        return Observable.create(subscriber -> {
            Log.d(TAG, "requestPersistentDataCalculation: request");
            ThreadUtils.pause(TIMEOUT);
            ArrayList<ApiConvertedModel> modelArrayList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                modelArrayList.add(new ApiConvertedModel(String.format(Locale.US, "api model name %d", i)));
            }
            subscriber.onNext(modelArrayList);
            subscriber.onCompleted();
            Log.d(TAG, "requestPersistentDataCalculation: onCompleted");
        });
    }
}
