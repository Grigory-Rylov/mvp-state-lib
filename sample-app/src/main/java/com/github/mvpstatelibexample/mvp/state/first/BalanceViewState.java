package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.AbsViewState;

/**
 * Created by grishberg on 26.01.17.
 */
public abstract class BalanceViewState extends AbsViewState {
    public static class UpdateBalanceState extends BalanceViewState {
        private final String balance;

        public UpdateBalanceState(String balance) {
            this.balance = balance;
        }

        public String getBalance() {
            return balance;
        }
    }
}
