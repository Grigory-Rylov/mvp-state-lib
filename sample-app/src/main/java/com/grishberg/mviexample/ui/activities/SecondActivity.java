package com.grishberg.mviexample.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grishberg.mviexample.R;
import com.grishberg.mviexample.mvp.presenters.SecondScreenPresenter;
import com.grishberg.mviexample.mvp.state.second.SecondPresenterState;
import com.grishberg.mviexample.mvp.state.second.SecondViewState;
import com.grishberg.mviexample.mvp.state.second.SecondViewState.NewValuesState;
import com.grishberg.mviexample.mvp.state.second.SecondViewState.ProgressState;
import com.grishberg.mvpstatelibrary.framework.state.MvpState;
import com.grishberg.mvpstatelibrary.framework.ui.BaseMvpActivity;

import java.util.List;
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
    public void onModelUpdated(final MvpState state) {
        if (state instanceof NewValuesState) {
            updateNewValues((NewValuesState) state);
        } else if (state instanceof ProgressState) {
            switchProgress((ProgressState) state);
        } else if (state instanceof SecondViewState.ErrorState) {
            showError();
        }
    }

    private void switchProgress(ProgressState state) {
        progressBar.setVisibility(state.isProgress() ? View.VISIBLE : View.GONE);
    }

    private void updateNewValues(NewValuesState state) {
        resultTextView.setText(String.format(Locale.US, "%d", state.getValues().size()));
    }

    private void showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }
}
