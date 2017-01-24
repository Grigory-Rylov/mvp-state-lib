package com.grishberg.mvpstatelibrary.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.view.BaseView;
import com.grishberg.mvpstatelibrary.framework.MvpDelegate;

import java.io.Serializable;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMvpActivity<P extends BaseMvpPresenter, S extends Serializable>
        extends AppCompatActivity implements BaseView<S> {
    private static final String TAG = BaseMvpActivity.class.getSimpleName();
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        presenter = (P) MvpDelegate.getPresenter(getClass());
        if (presenter == null) {
            Log.d(TAG, "onCreate: presenter is ");
            presenter = createPresenter();
            MvpDelegate.putPresenter(getClass(), presenter);
        }
        presenter.attachView(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        presenter.saveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if (isFinishing()) {
            Log.d(TAG, "onDestroy: is finishing");
            MvpDelegate.removePresenter(getClass());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        presenter.attachView(this, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        presenter.detachView();
        if (isFinishing()) {
            Log.d(TAG, "onPause: is finishing");
            MvpDelegate.removePresenter(getClass());
        }
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();
}
