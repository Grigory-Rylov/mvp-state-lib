package com.grishberg.mvpstatelibrary.framework.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.grishberg.mvpstatelibrary.framework.MvpDelegate;
import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObservable;
import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObserver;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;

/**
 * Created by grishberg on 26.01.17.
 */
public class MvpHelper<P extends BaseMvpPresenter> implements LifeCycleObserver {
    private static final String TAG = MvpHelper.class.getSimpleName();
    private LifeCycleObservable parent;
    private P presenter;
    private final DelegateTagHolder tagHolder;
    private String delegateTag;

    public MvpHelper(@NonNull DelegateTagHolder tagHolder) {
        this.tagHolder = tagHolder;
        presenter = (P) MvpDelegate.getPresenter(getDelegateTag());
    }

    public void setPresenter(final P presenter) {
        Log.d(TAG, "setPresenter: presenter created ");
        this.presenter = presenter;
        MvpDelegate.putPresenter(getDelegateTag(), presenter);
    }

    public void registerNestedView(final LifeCycleObservable parent, Bundle savedInstanceState) {
        this.parent = parent;
        parent.registerObserver(this);

        presenter.restoreState(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle state) {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        presenter.subscribe(tagHolder);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        presenter.unSubscribe(tagHolder);
        if (parent.isFinishing()) {
            Log.d(TAG, "onPause: is finishing");
            MvpDelegate.removePresenter(getDelegateTag());
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if (parent.isFinishing()) {
            Log.d(TAG, "onDestroy: is finishing");
            MvpDelegate.removePresenter(getDelegateTag());
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        presenter.saveInstanceState(outState);
    }

    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: ");
        if (parent != null) {
            parent.registerObserver(this);
        }
        presenter.subscribe(tagHolder);
    }

    public void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: ");
        if (parent != null) {
            parent.unRegisterObserver(this);
        }
        presenter.unSubscribe(tagHolder);
    }

    public P getPresenter() {
        return presenter;
    }

    public String getDelegateTag() {
        if (delegateTag == null) {
            final StringBuilder sb = new StringBuilder(getClass().getName());
            sb.append(":");
            sb.append(tagHolder.getId());
            delegateTag = sb.toString();
        }

        return delegateTag;
    }
}
