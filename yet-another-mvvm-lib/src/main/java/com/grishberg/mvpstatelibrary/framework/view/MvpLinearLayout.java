package com.grishberg.mvpstatelibrary.framework.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObservable;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;

import java.io.Serializable;

/**
 * Created by grishberg on 26.01.17.
 * Base LinearLayoutView
 */
public abstract class MvpLinearLayout<P extends BaseMvpPresenter, VS extends Serializable>
        extends LinearLayout implements StateObserver<VS>, DelegateTagHolder<VS> {

    private MvpHelper<P> helper;

    public MvpLinearLayout(Context context) {
        this(context, null, 0);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        helper = new MvpHelper<>(this);
        if (helper.getPresenter() == null) {
            helper.setPresenter(providePresenter());
        }
    }

    public void registerNestedView(final LifeCycleObservable parent, Bundle savedInstanceState) {
        helper.registerNestedView(parent, savedInstanceState);
    }

    protected abstract P providePresenter();

    protected P getPresenter() {
        return helper.getPresenter();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        helper.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        helper.onDetachedFromWindow();
    }

}
