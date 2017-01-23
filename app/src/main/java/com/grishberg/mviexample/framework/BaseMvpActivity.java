package com.grishberg.mviexample.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by grishberg on 22.01.17.
 */
public abstract class BaseMvpActivity<P extends BaseMviPresenter, S extends Serializable>
        extends AppCompatActivity implements BaseView<S> {
    private static final String TAG = BaseMvpActivity.class.getSimpleName();
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (P) MvpDelegate.getPresenter(getClass());
        if (presenter == null) {
            presenter = createPresenter();
            MvpDelegate.putPresenter(getClass(), presenter);
        }
        presenter.attachView(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            Log.d(TAG, "onDestroy: is finishing");
            MvpDelegate.putPresenter(getClass(), null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
        if (isFinishing()) {
            Log.d(TAG, "onPause: is finishing");
            MvpDelegate.putPresenter(getClass(), null);
        }
    }

    protected abstract P createPresenter();
}
