package com.github.mvpstatelibexample.mvp.state.balance;

import com.github.mvpstatelib.framework.state.MvpState;

/**
 * Created by grishberg on 26.01.17.
 */
public interface BalanceViewState extends MvpState {
    class UpdateBalanceState implements BalanceViewState {
        private final String balance;

        public UpdateBalanceState(String balance) {
            this.balance = balance;
        }

        public String getBalance() {
            return balance;
        }
    }
}
