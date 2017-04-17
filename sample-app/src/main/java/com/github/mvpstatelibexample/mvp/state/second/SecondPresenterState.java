package com.github.mvpstatelibexample.mvp.state.second;

import com.github.mvpstatelib.framework.state.MvpState;

import java.util.List;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public interface SecondPresenterState extends MvpState {
    class RequestState implements SecondPresenterState{
    }

    class ResponseState implements SecondPresenterState{
        private final List<String> values;

        public ResponseState(List<String> values) {
            this.values = values;
        }

        public List<String> getValues() {
            return values;
        }
    }
}
