package com.grishberg.mviexample.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grishberg.datafacade.ListResultCloseable;
import com.grishberg.mviexample.R;
import com.grishberg.mvpstatelibrary.framework.BaseMvpActivity;
import com.grishberg.mviexample.mvp.presenters.SecondScreenPresenter;
import com.grishberg.mviexample.mvp.state.presenter.SecondPresenterStateModel;
import com.grishberg.mviexample.mvp.state.view.SecondViewStateModel;
import com.grishberg.mviexample.mvp.view.SecondView;

import java.io.IOException;
import java.util.Locale;

public class SecondActivity extends BaseMvpActivity<SecondScreenPresenter, SecondViewStateModel>
        implements SecondView {
    private static final String TAG = SecondActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView resultTextView;
    private ListResultCloseable<String> values;

    public static void start(final Context context) {
        context.startActivity(new Intent(context, SecondActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                getPresenter().updateState(SecondPresenterStateModel.makeFromClick())
        );
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateView(final SecondViewStateModel viewStateModel) {
        if (viewStateModel.isError()) {
            showError();
            return;
        }

        progressBar.setVisibility(viewStateModel.isProgress() ? View.VISIBLE : View.GONE);

        if (viewStateModel.isProgress()) {
            return;
        }

        this.values = viewStateModel.getValues();
        resultTextView.setText(String.format(Locale.US, "%d", values.size()));
    }

    private void showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (values != null) {
            try {
                values.close();
            } catch (final IOException e) {
                Log.e(TAG, "onDestroy: ", e);
            }
        }
    }

    @Override
    protected SecondScreenPresenter createPresenter() {
        return new SecondScreenPresenter();
    }
}
