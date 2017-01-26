package com.grishberg.mvpstatelibrary.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObservable;
import com.grishberg.mvpstatelibrary.framework.lifecycle.LifeCycleObserver;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;
import com.grishberg.mvpstatelibrary.framework.view.BaseView;
import com.grishberg.mvpstatelibrary.framework.MvpDelegate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMvpActivity<P extends BaseMvpPresenter, VS extends Serializable>
        extends AppCompatActivity implements StateObserver<VS>, LifeCycleObservable {
    private static final String TAG = BaseMvpActivity.class.getSimpleName();
    private P presenter;
    private ArrayList<LifeCycleObserver> observers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        presenter = (P) MvpDelegate.getPresenter(getDelegateTag());
        if (presenter == null) {
            Log.d(TAG, "onCreate: presenter is ");
            presenter = createPresenter();
            MvpDelegate.putPresenter(getDelegateTag(), presenter);
        }
        presenter.restoreState(savedInstanceState);

        //TODO: maybe do not need
        for (LifeCycleObserver observer : observers) {
            observer.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        presenter.saveInstanceState(outState);

        for (LifeCycleObserver observer : observers) {
            observer.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        presenter.subscribe(this);

        for (LifeCycleObserver observer : observers) {
            observer.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        presenter.unSubscribe(this);
        if (isFinishing()) {
            Log.d(TAG, "onPause: is finishing");
            MvpDelegate.removePresenter(getDelegateTag());
        }
        for (LifeCycleObserver observer : observers) {
            observer.onPause();
        }
    }

    @Override
    public String getDelegateTag() {
        return getClass().getName();
    }

    @Override
    public void registerObserver(LifeCycleObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unRegisterObserver(LifeCycleObserver observer) {
        observers.remove(observer);
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();
}
