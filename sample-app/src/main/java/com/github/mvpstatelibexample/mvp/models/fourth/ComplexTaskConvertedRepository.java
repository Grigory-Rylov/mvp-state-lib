package com.github.mvpstatelibexample.mvp.models.fourth;

import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;

import java.util.List;

import rx.Observable;

/**
 * Created by grishberg on 23.04.17.
 */

public interface ComplexTaskConvertedRepository {
    Observable<List<ApiConvertedModel>> requestCalculatedData();
}
