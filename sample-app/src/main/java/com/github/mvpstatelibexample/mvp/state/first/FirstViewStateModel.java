package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.AbsMvpState;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class FirstViewStateModel extends AbsMvpState {
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
