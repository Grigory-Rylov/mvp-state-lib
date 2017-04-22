package com.github.mvpstatelibexample.ui.fragments.first;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.mvp.presenters.FirstFragmentPresenter;
import com.github.mvpstatelib.framework.state.MvpState;
import com.github.mvpstatelib.framework.ui.BaseMvpFragment;
import com.github.mvpstatelibexample.ui.activities.third.FragmentInteractionListener;

import java.util.Locale;

public class FirstFragment extends BaseMvpFragment<FirstFragmentPresenter> {
    private static final String ARG_POSITION = "argPosition";

    private int position;

    private FragmentInteractionListener listener;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(final int position) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_first, container, false);
        TextView textView = (TextView) inflate.findViewById(R.id.fragment_first_text);
        textView.setText(String.format(Locale.US, "%d", position));
        return inflate;
    }

    @Override
    protected FirstFragmentPresenter createPresenter() {
        return new FirstFragmentPresenter();
    }

    @Override
    public void onModelUpdated(MvpState viewStateModel) {
        //TODO: add some action when state received
    }

    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentAction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            listener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
