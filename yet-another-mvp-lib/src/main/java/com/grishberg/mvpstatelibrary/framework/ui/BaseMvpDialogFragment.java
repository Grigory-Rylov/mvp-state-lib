package com.grishberg.mvpstatelibrary.framework.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.grishberg.mvpstatelibrary.framework.MvpDelegate;
import com.grishberg.mvpstatelibrary.framework.presenter.BaseMvpPresenter;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.state.StateObserver;

/**
 * Created by grishberg on 01.04.17.
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpDialogFragment<P extends BaseMvpPresenter> extends DialogFragment
        implements StateObserver {
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
    public void onModelUpdated(MvpState model) {

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
