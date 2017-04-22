package com.github.mvpstatelibexample.mvp.state.balance;

import com.github.mvpstatelib.framework.state.AbsMvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public abstract class BalanceViewState extends AbsMvpState {
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
