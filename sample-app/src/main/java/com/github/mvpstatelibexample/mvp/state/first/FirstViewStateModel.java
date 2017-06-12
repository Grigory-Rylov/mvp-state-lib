package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.AbsViewState;
import com.github.mvpstatelib.framework.state.ViewState;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class FirstViewStateModel extends AbsViewState {
    public static class SuccessState extends FirstViewStateModel {
        private final String title;
        private final String description;
        private final int count;

        public SuccessState(String title, String description, int count) {
            this.title = title;
            this.description = description;
            this.count = count;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCount() {
            return count;
        }

        @Override
        public ViewState reduct(ViewState viewState) {
            if (viewState instanceof ProgressState) {
                SuccessState successState = new SuccessState(this.title, this.description, this.count);
                return successState;
            }
            return super.reduct(viewState);
        }
    }

    public static class ErrorState extends FirstViewStateModel {
    }

    public static class ProgressState extends FirstViewStateModel {
        private final boolean isProgress;

        public ProgressState(boolean isProgress) {
            this.isProgress = isProgress;
        }

        public boolean isProgress() {
            return isProgress;
        }
    }
}
