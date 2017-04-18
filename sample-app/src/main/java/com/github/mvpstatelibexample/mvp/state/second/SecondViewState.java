package com.github.mvpstatelibexample.mvp.state.second;

import com.github.mvpstatelib.framework.state.ModelWithNonSerializable;
import com.github.mvpstatelib.framework.state.MvpState;

import java.util.List;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public interface SecondViewState extends MvpState {
    class NewValuesState implements SecondViewState , ModelWithNonSerializable {
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

    class ProgressState implements SecondViewState {
        private final boolean isProgress;

        public ProgressState(boolean isProgress) {
            this.isProgress = isProgress;
        }

        public boolean isProgress() {
            return isProgress;
        }
    }

    class ErrorState implements SecondViewState {
    }
}
