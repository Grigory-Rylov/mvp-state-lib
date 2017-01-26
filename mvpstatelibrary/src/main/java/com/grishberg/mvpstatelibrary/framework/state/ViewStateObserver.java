package com.grishberg.mvpstatelibrary.framework.state;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 26.01.17.
 */

public interface ViewStateObserver<T, P extends BaseMvpPresenter> extends StateObserver<T> {
    P providePresenter();
}
