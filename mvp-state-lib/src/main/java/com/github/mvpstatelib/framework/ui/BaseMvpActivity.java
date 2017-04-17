package com.github.mvpstatelib.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mvpstatelib.framework.lifecycle.LifeCycleObservable;
import com.github.mvpstatelib.framework.lifecycle.LifeCycleObserver;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.StateObserver;
import com.github.mvpstatelib.framework.MvpDelegate;

import java.util.HashSet;

/**
 * Created by grishberg on 22.01.17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpActivity<P extends BaseMvpPresenter>
        extends AppCompatActivity implements StateObserver, LifeCycleObservable {
    private P presenter;
    private HashSet<LifeCycleObserver> observers = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (P) MvpDelegate.getPresenter(getDelegateTag());
        if (presenter == null) {
            presenter = createPresenter();
            MvpDelegate.putPresenter(getDelegateTag(), presenter);
        }
        presenter.restoreState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveInstanceState(outState);

        for (LifeCycleObserver observer : observers) {
            observer.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe(this);

        for (LifeCycleObserver observer : observers) {
            observer.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unSubscribe(this);
        if (isFinishing()) {
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
