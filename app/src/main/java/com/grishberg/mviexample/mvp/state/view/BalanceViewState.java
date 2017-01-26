package com.grishberg.mviexample.mvp.state.view;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public class BalanceViewState implements MvpState {
    private static final String TAG = BalanceViewState.class.getSimpleName();
    private String balance;

    public String getBalance() {
        return balance;
    }

    public BalanceViewState setBalance(String balance) {
        this.balance = balance;
        return this;
    }
}
