package com.github.mvpstatelibexample.ui.activities.second;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.mvp.presenters.second.SecondScreenPresenter;
import com.github.mvpstatelibexample.mvp.state.second.SecondPresenterState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState.NewValuesState;
import com.github.mvpstatelibexample.mvp.state.second.SecondViewState.ProgressState;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.ui.BaseMvpActivity;

import java.util.Locale;

public class SecondActivity extends BaseMvpActivity<SecondScreenPresenter> {
    private ProgressBar progressBar;
    private TextView resultTextView;

    public static void start(final Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        initWidgets();
        initButtonHandlers();
    }

    private void initWidgets() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    private void initButtonHandlers() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                getPresenter().updateState(new SecondPresenterState.RequestState())
        );
    }

    @Override
    protected SecondScreenPresenter createPresenter() {
        return new SecondScreenPresenter();
    }

    @Override
    public void onStateUpdated(final MvpState state) {
        GeneratedSecondActivitySubscriber.processState(this, state);
    }

    @SubscribeState
    void switchProgress(ProgressState state) {
        progressBar.setVisibility(state.isProgress() ? View.VISIBLE : View.GONE);
    }

    @SubscribeState
    void updateNewValues(NewValuesState state) {
        resultTextView.setText(String.format(Locale.US, "%d", state.getValues().size()));
    }

    @SubscribeState
    void showError(SecondViewState.ErrorState state) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }
}
