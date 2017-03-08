package com.grishberg.mviexample.mvp.state.second;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

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
