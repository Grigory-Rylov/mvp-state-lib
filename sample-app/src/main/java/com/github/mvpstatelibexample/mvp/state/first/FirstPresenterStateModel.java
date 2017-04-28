package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.AbsPresenterState;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public abstract class FirstPresenterStateModel extends AbsPresenterState {

    public static class RequestState extends FirstPresenterStateModel {
    }

    public static class ResponseState extends FirstPresenterStateModel{
        private final String title;
        private final String description;
        private final int count;

        public ResponseState(String title, String description, int count) {
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
}
