package com.grishberg.mvpstatelibrary.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.grishberg.mvpstatelibrary.framework.MvpDelegate;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.view.BaseView;

import java.io.Serializable;

/**
 * Created by grishberg on 24.01.17.
 */
public abstract class BaseMvpFragment<P extends BaseMvpPresenter, S extends Serializable>
        extends Fragment implements BaseView<S> {
    private static final String TAG = BaseMvpFragment.class.getSimpleName();
    private P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        presenter = (P) MvpDelegate.getPresenter(getFragmentTag());
        if (presenter == null) {
            Log.d(TAG, "onCreate: presenter is ");
            presenter = createPresenter();
            MvpDelegate.putPresenter(getFragmentTag(), presenter);
        }
        presenter.restoreState(savedInstanceState);
    }

    private String getFragmentTag() {
        String tag = getTag();
        if (tag == null) {
            tag = getClass().getName();
        }
        return tag;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        presenter.saveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if (getActivity().isFinishing()) {
            Log.d(TAG, "onDestroy: is finishing");
            MvpDelegate.removePresenter(getFragmentTag());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        presenter.detachView();
        if (getActivity().isFinishing()) {
            Log.d(TAG, "onPause: is finishing");
            MvpDelegate.removePresenter(getFragmentTag());
        }
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();
}
