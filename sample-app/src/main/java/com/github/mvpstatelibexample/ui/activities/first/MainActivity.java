package com.github.mvpstatelibexample.ui.activities.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mvpstatelib.framework.state.ViewState;
import com.github.mvpstatelib.state.annotations.SubscribeState;
import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.mvp.presenters.first.FirstScreenPresenter;
import com.github.mvpstatelibexample.mvp.state.first.FirstPresenterStateModel.RequestState;
import com.github.mvpstatelibexample.mvp.state.first.FirstViewStateModel.ErrorState;
import com.github.mvpstatelibexample.mvp.state.first.FirstViewStateModel.ProgressState;
import com.github.mvpstatelibexample.mvp.state.first.FirstViewStateModel.SuccessState;
import com.github.mvpstatelibexample.ui.activities.fourth.ComplexTaskScreen;
import com.github.mvpstatelibexample.ui.activities.second.SecondActivity;
import com.github.mvpstatelibexample.ui.activities.third.ThirdActivity;
import com.github.mvpstatelibexample.ui.view.first.BalanceView;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.ui.BaseMvpActivity;

import java.util.Locale;

/**
 * FirstScreen View
 */
public class MainActivity extends BaseMvpActivity<FirstScreenPresenter>
        implements View.OnClickListener {
    private ProgressBar progressBar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView countTextView;
    private Button buttonStart;
    private Button buttonSecondStep;
    private Button buttonThirdStep;
    private Button buttonComplexTaskStep;
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
        buttonComplexTaskStep = (Button) findViewById(R.id.buttonComplexTaskStep);
    }

    private void initButtonHandlers() {
        buttonStart.setOnClickListener(this);
        buttonSecondStep.setOnClickListener(this);
        buttonThirdStep.setOnClickListener(this);
        buttonComplexTaskStep.setOnClickListener(this);
    }

    @Override
    protected FirstScreenPresenter createPresenter() {
        return new FirstScreenPresenter();
    }

    /**
     * Update view after changes from presenter
     *
     * @param state - model with updates from presenter
     */
    @Override
    public void onStateUpdated(final ViewState state) {
        GeneratedMainActivitySubscriber.processState(this, state);
    }

    @SubscribeState
    void showProgress(ProgressState state) {
        progressBar.setVisibility(state.isProgress() ? View.VISIBLE : View.GONE);
        if (state.isProgress()) {
            buttonStart.setEnabled(false);
            buttonSecondStep.setEnabled(false);
        }
    }

    @SubscribeState
    void updateValues(SuccessState state) {
        titleTextView.setText(state.getTitle());
        descriptionTextView.setText(state.getDescription());
        countTextView.setText(String.format(Locale.US, "%d", state.getCount()));
        buttonSecondStep.setEnabled(true);
    }

    @SubscribeState
    void showError(ErrorState state) {
        buttonStart.setEnabled(true);
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                getPresenter().updateState(new RequestState());
                break;

            case R.id.buttonSecondStep:
                SecondActivity.start(this);
                break;

            case R.id.buttonComplexTaskStep:
                ComplexTaskScreen.start(this);
                break;

            default:
                ThirdActivity.start(this);
                break;
        }
    }
}
