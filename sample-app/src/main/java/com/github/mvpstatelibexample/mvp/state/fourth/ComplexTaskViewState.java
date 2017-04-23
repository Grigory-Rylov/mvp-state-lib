package com.github.mvpstatelibexample.mvp.state.fourth;

import com.github.mvpstatelib.framework.state.AbsMvpState;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;

import java.util.List;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexTaskViewState extends AbsMvpState {
    public static class ComplexTaskDataDownloadingState extends ComplexTaskViewState {
        private final int processedCount;
        private boolean showRequest;

        public ComplexTaskDataDownloadingState(int processedCount) {
            this.processedCount = processedCount;
            showRequest = true;
        }

        public boolean isShowRequest() {
            return showRequest;
        }

        public int getProcessedCount() {
            return processedCount;
        }
    }

    public static class UpdateSecondStepState extends ComplexTaskViewState {
        private final List<ApiConvertedModel> modelListLeft;
        private final int processedCount;

        public UpdateSecondStepState(List<ApiConvertedModel> modelListLeft, int proccessedCount) {
            this.modelListLeft = modelListLeft;
            this.processedCount = proccessedCount;
        }

        public int getProcessedCount() {
            return processedCount;
        }

        public List<ApiConvertedModel> getModelListLeft() {
            return modelListLeft;
        }
    }

    public static class CompleteSecondStepState extends ComplexTaskViewState {
    }
}
