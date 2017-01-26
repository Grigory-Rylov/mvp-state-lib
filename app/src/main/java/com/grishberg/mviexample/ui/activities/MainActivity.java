package com.grishberg.mviexample.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grishberg.mviexample.R;
import com.grishberg.mviexample.ui.view.BalanceView;
import com.grishberg.mvpstatelibrary.framework.ui.BaseMvpActivity;
import com.grishberg.mviexample.mvp.presenters.FirstScreenPresenter;
import com.grishberg.mviexample.mvp.state.presenter.FirstPresenterStateModel;
import com.grishberg.mviexample.mvp.state.view.FirstViewStateModel;

import java.util.Locale;

/**
 * FirstScreen View
 */
public class MainActivity extends BaseMvpActivity<FirstScreenPresenter, FirstViewStateModel>
        implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView countTextView;
    private Button buttonStart;
    private Button buttonSecondStep;
    private Button buttonThirdStep;
    private BalanceView balanceView; // view of nested presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initWidgets();
        initButtonHandlers();
        //register view of nested presenter to parent life cycle
        balanceView.registerNestedView(this, savedInstanceState);
    }

    private void initWidgets() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        countTextView = (TextView) findViewById(R.id.countTextView);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonSecondStep = (Button) findViewById(R.id.buttonSecondStep);
        buttonThirdStep = (Button) findViewById(R.id.buttonThirdStep);
        balanceView = (BalanceView) findViewById(R.id.balanceView);
    }

    private void initButtonHandlers() {
        buttonStart.setOnClickListener(this);
        buttonSecondStep.setOnClickListener(this);
        buttonThirdStep.setOnClickListener(this);
    }

    @Override
    protected FirstScreenPresenter createPresenter() {
        return new FirstScreenPresenter();
    }

    /**
     * Update view after changes from presenter
     *
     * @param viewStateModel - model with updates from presenter
     */
    @Override
    public void onModelUpdated(final FirstViewStateModel viewStateModel) {
        buttonStart.setEnabled(false);
        buttonSecondStep.setEnabled(false);
        if (viewStateModel.isError()) {
            buttonStart.setEnabled(true);
            showError();
            return;
        }

        progressBar.setVisibility(viewStateModel.isProgress() ? View.VISIBLE : View.GONE);

        if (viewStateModel.isProgress()) {
            // if isProgress than no data for show
            return;
        }
        // update views for response
        titleTextView.setText(viewStateModel.getTitle());
        descriptionTextView.setText(viewStateModel.getDescription());
        countTextView.setText(String.format(Locale.US, "%d", viewStateModel.getCount()));
        buttonSecondStep.setEnabled(true);
    }

    private void showError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                getPresenter().updateState(FirstPresenterStateModel.makeClick());
                break;

            case R.id.buttonSecondStep:
                SecondActivity.start(this);
                break;

            default:
                ThirdActivity.start(this);
                break;
        }
    }
}
