package com.github.mvpstatelibexample.ui.view.first;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.mvp.presenters.first.BalanceViewPresenter;
import com.github.mvpstatelibexample.mvp.state.first.BalancePresenterState;
import com.github.mvpstatelibexample.mvp.state.first.BalanceViewState.UpdateBalanceState;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.view.MvpLinearLayout;

/**
 * Created by grishberg on 26.01.17.
 * Example nested view with presenter
 */
public class BalanceView extends MvpLinearLayout<BalanceViewPresenter> {
    private static final String TAG = BalanceView.class.getSimpleName();
    private TextView balanceTextView;

    public BalanceView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        View rootView = inflate(context, R.layout.view_custom_balance, this);
        balanceTextView = (TextView) rootView.findViewById(R.id.custom_box_text);

        getPresenter().updateState(new BalancePresenterState.RequestState());
    }

    @Override
    public void onStateUpdated(final MvpState state) {
        Log.d(TAG, "onStateUpdated: " + state);
        if(state instanceof UpdateBalanceState) {
            updateBalance((UpdateBalanceState) state);
        }
    }

    private void updateBalance(UpdateBalanceState state) {
        balanceTextView.setText(state.getBalance());
    }

    @Override
    protected BalanceViewPresenter providePresenter() {
        return new BalanceViewPresenter();
    }
}
