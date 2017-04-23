package com.github.mvpstatelibexample.ui.activities.fourth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.ui.BaseMvpActivity;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.di.MyServiceLocator;
import com.github.mvpstatelibexample.mvp.presenters.fourh.ComplexTaskPresenter;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskPresenterState;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskViewState.*;
import com.github.mvpstatelibexample.mvp.state.fourth.ComplexTaskViewState.ComplexTaskDataDownloadingState;
import com.github.mvpstatelibexample.ui.adapters.fourth.LeftItemsAdapter;
import com.github.mvpstatelibexample.ui.fragments.fourth.ConfirmDialogFragment;
import com.github.mvpstatelibexample.utils.LoggerCat;

/**
 * Created by grishberg on 22.04.17.
 */

public class ComplexTaskScreen extends BaseMvpActivity<ComplexTaskPresenter> {
    private static final String TAG = ComplexTaskScreen.class.getSimpleName();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LeftItemsAdapter adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ComplexTaskScreen.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_task_screen);
        progressBar = (ProgressBar) findViewById(R.id.complexTaskScreenProgress);
        recyclerView = (RecyclerView) findViewById(R.id.complexTaskScreenRecycler);
        adapter = new LeftItemsAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (savedInstanceState == null) {
            getPresenter().updateState(new ComplexTaskPresenterState.StartScreenState());
        }
    }

    @Override
    public void onStateUpdated(MvpState model) {
        GeneratedComplexTaskScreenSubscriber.processState(this, model);
    }

    @SubscribeState
    void onReceivedResponseFromStepTwo(ComplexTaskDataDownloadingState state) {
        if (state.isShowRequest()) {
            showConfirmDialog(state.getProcessedCount());
        }
    }

    private void showConfirmDialog(int processedCount) {
        ConfirmDialogFragment fragment = (ConfirmDialogFragment) getSupportFragmentManager()
                .findFragmentByTag(ConfirmDialogFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = ConfirmDialogFragment.newInstance(processedCount);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(fragment, ConfirmDialogFragment.class.getSimpleName())
                    .commitNow();
            getSupportFragmentManager()
                    .executePendingTransactions();
        }
        fragment.setListener(new ConfirmDialogFragment.YesNoListener() {
            @Override
            public void onYes() {
                getPresenter().updateState(new ComplexTaskPresenterState.ConfirmSecondStepState(true));
            }

            @Override
            public void onNo() {

            }
        });
    }

    @SubscribeState
    void onSecondStepDataUpdated(UpdateSecondStepState state) {
        Log.d(TAG, "onSecondStepDataUpdated: " + state.getProcessedCount());
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, String.format(
                getString(R.string.complex_task_step_update_text), state.getProcessedCount()),
                Toast.LENGTH_SHORT
        ).show();
        adapter.setItems(state.getModelListLeft());
    }

    @SubscribeState
    void onSecondStepStateCompleted(CompleteSecondStepState state) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, "onSeconStepStateCompleted: ");
    }

    @Override
    protected ComplexTaskPresenter createPresenter() {
        return new ComplexTaskPresenter(MyServiceLocator.provideComplexScreenInteractor(), new LoggerCat());
    }
}
