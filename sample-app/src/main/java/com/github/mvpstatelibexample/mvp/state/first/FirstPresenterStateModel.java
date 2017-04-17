package com.github.mvpstatelibexample.mvp.state.first;

import com.github.mvpstatelib.framework.state.MvpState;

/**
 * Created on 08.03.17.
 *
 * @author g
 */
public interface FirstPresenterStateModel extends MvpState {

    class RequestState implements FirstPresenterStateModel {
    }

    class ResponseState implements FirstPresenterStateModel{
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
