package com.github.mvpstatelibexample.mvp.state.second;

import com.github.mvpstatelib.framework.state.ModelWithNonSerializable;
import com.github.mvpstatelib.framework.state.MvpState;

import java.util.List;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class SecondViewState implements MvpState {
    public static class NewValuesState extends SecondViewState implements ModelWithNonSerializable<List<String>> {
        private final transient List<String> values;

        public NewValuesState(List<String> values) {
            this.values = values;
        }

        @Override
        public boolean isNonSerializableNull() {
            return values == null;
        }

        @Override
        public void setNonSerializableValue(List<String> value) {
            //TODO: remove
        }

        public List<String> getValues() {
            return values;
        }
    }

    public static class ProgressState extends SecondViewState {
        private final boolean isProgress;

        public ProgressState(boolean isProgress) {
            this.isProgress = isProgress;
        }

        public boolean isProgress() {
            return isProgress;
        }
    }

    public static class ErrorState extends SecondViewState {
    }
}
