package com.grishberg.mviexample.mvp.state.first;

import com.grishberg.mvpstatelibrary.framework.state.MvpState;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public interface FirstViewStateModel extends MvpState {
    class SuccessState implements FirstViewStateModel {
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
    }

    class ErrorState implements FirstViewStateModel {
    }

    class ProgressState implements FirstViewStateModel {
        private final boolean isProgress;

        public ProgressState(boolean isProgress) {
            this.isProgress = isProgress;
        }

        public boolean isProgress() {
            return isProgress;
        }
    }
}
