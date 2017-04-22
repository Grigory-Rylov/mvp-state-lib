package com.github.mvpstatelibexample.mvp.state.second;

import com.github.mvpstatelib.framework.state.AbsMvpState;
import com.github.mvpstatelib.framework.state.ModelWithNonSerializable;

import java.util.List;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class SecondViewState extends AbsMvpState {
    public static class NewValuesState extends SecondViewState implements ModelWithNonSerializable {
        private final transient List<String> values;

        public NewValuesState(List<String> values) {
            this.values = values;
        }

        @Override
        public boolean isNonSerializableNull() {
            return values == null;
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
