package com.github.mvpstatelib.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.github.mvpstatelib.framework.MvpDelegate;
import com.github.mvpstatelib.framework.presenter.BaseMvpPresenter;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.state.StateObserver;
import com.github.mvpstatelib.framework.state.ViewState;

/**
 * Created by grishberg on 01.04.17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpDialogFragment<P extends BaseMvpPresenter> extends DialogFragment
        implements StateObserver<ViewState> {
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

    @Override
    public void onStateUpdated(ViewState state) {
    }

    private String getFragmentTag() {
        String tag = getTag();
        if (tag == null) {
            tag = getClass().getName();
        }
        return tag;
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveInstanceState(outState);
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();
}
