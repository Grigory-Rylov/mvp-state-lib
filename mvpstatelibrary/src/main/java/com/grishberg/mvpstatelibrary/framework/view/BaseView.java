package com.grishberg.mvpstatelibrary.framework.view;

/**
 * Created by grishberg on 22.01.17.
 * Base View interface
 */
public interface BaseView<S>{

    void updateView(final S viewStateModel);
}
