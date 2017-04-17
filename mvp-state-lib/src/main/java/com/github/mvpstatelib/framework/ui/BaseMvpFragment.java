package com.github.mvpstatelib.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.mvpstatelib.framework.MvpDelegate;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.StateObserver;

/**
 * Created by grishberg on 24.01.17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpFragment<P extends BaseMvpPresenter>
        extends Fragment implements StateObserver {
    private P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (P) MvpDelegate.getPresenter(getFragmentTag());
        if (presenter == null) {
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
        presenter.saveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isFinishing()) {
            MvpDelegate.removePresenter(getFragmentTag());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe(this);
        if (getActivity().isFinishing()) {
            MvpDelegate.removePresenter(getFragmentTag());
        }
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();
}
