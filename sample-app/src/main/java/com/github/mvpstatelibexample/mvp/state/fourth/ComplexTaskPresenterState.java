package com.github.mvpstatelibexample.mvp.state.fourth;

import com.github.mvpstatelib.framework.state.AbsPresenterState;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;

import java.util.List;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexTaskPresenterState extends AbsPresenterState {
    public static class StartScreenState extends ComplexTaskPresenterState {
    }

    public static class PersistentStoreResponse extends ComplexTaskPresenterState {
        private final List<ApiConvertedModel> modelList;

        public PersistentStoreResponse(List<ApiConvertedModel> modelList) {
            this.modelList = modelList;
        }

        public List<ApiConvertedModel> getModelList() {
            return modelList;
        }
    }

    public static class ConfirmSecondStepState extends ComplexTaskPresenterState {
        private final boolean confirmed;

        public ConfirmSecondStepState(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public boolean isConfirmed() {
            return confirmed;
        }
    }

    public static class UpdateSecondStepState extends ComplexTaskPresenterState {
        private final List<ApiConvertedModel> modelListLeft;
        private final int processedCount;

        public UpdateSecondStepState(List<ApiConvertedModel> modelListLeft, int processedCount) {
            this.modelListLeft = modelListLeft;
            this.processedCount = processedCount;
        }

        public List<ApiConvertedModel> getModelListLeft() {
            return modelListLeft;
        }

        public int getProcessedCount() {
            return processedCount;
        }
    }

    public static class CompletedSecondStepState extends ComplexTaskPresenterState {
    }

    public static class UpdateSecondStepNetworkError extends ComplexTaskPresenterState {
    }

    public static class UpdateSecondStepUnknownError extends ComplexTaskPresenterState {
    }
}
