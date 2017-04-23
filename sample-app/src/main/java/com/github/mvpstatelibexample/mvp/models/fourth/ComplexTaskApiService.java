package com.github.mvpstatelibexample.mvp.models.fourth;

import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.PersistentModel;

import java.util.List;

import rx.Observable;

/**
 * Created by grishberg on 22.04.17.
 */

public interface ComplexTaskApiService {
    Observable<List<ApiConvertedModel>> requestPersistentDataCalculation(List<PersistentModel> response);
}
