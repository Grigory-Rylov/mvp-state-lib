package com.grishberg.mvpstatelibrary.framework.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.grishberg.mvpstatelibrary.framework.MvpDelegate;
import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObservable;
import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObserver;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;

import java.io.Serializable;

/**
 * Created by grishberg on 26.01.17.
 * Base LinearLayoutView
 */
public abstract class MvpLinearLayout<P extends BaseMvpPresenter, VS extends Serializable>
        extends LinearLayout implements StateObserver<VS>, LifeCycleObserver {
    private static final String TAG = MvpLinearLayout.class.getSimpleName();
    private P presenter;
    private String parentTag;
    private LifeCycleObservable parent;

    public MvpLinearLayout(Context context) {
        this(context, null, 0);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void registerNestedView(final LifeCycleObservable parent, Bundle savedInstanceState) {
        this.parent = parent;
        parent.registerObserver(this);
        parentTag = parent.getDelegateTag();

        presenter = (P) MvpDelegate.getPresenter(getDelegateTag());
        if (presenter == null) {
            Log.d(TAG, "registerNestedView: presenter is ");
            presenter = providePresenter();
            MvpDelegate.putPresenter(getDelegateTag(), presenter);
        }
        presenter.restoreState(savedInstanceState);
    }

    protected abstract P providePresenter();

    protected P getPresenter() {
        return presenter;
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: ");
        super.onAttachedToWindow();
        if (parent != null) {
            parent.registerObserver(this);
        }
        //TODO: presenter.subscribe(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
        parent.unRegisterObserver(this);
        //TODO: presenter.unSubscribe(this);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        presenter.saveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle state) {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        presenter.subscribe(this);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        presenter.unSubscribe(this);
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

    private String getDelegateTag() {
        final StringBuilder sb = new StringBuilder(parentTag);
        sb.append(":");
        sb.append(getId());
        return sb.toString();
    }
}
