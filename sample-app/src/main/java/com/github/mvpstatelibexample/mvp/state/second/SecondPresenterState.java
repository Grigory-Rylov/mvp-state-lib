package com.github.mvpstatelibexample.mvp.state.second;

import com.github.mvpstatelib.framework.state.AbsPresenterState;

import java.util.List;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class SecondPresenterState extends AbsPresenterState {
    public static class RequestState extends SecondPresenterState{
    }

    public static class ResponseState extends SecondPresenterState{
        private final List<String> values;

        public ResponseState(List<String> values) {
            this.values = values;
        }

        public List<String> getValues() {
            return values;
        }
    }
}
