package com.github.mvpstatelibexample.ui.fragments.fourth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.github.mvpstatelibexample.R;

/**
 * Created by grishberg on 23.04.17.
 */

public class ConfirmDialogFragment extends DialogFragment {

    private static final String ARG_COUNT = "ARG_COUNT";
    private YesNoListener listener;

    public static ConfirmDialogFragment newInstance(int count) {
        ConfirmDialogFragment dialog = new ConfirmDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, count);
        dialog.setArguments(args);
        return dialog;
    }

    public void setListener(YesNoListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int count = getArguments().getInt(ARG_COUNT);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.confirm_dialog_title)
                .setMessage(String.format(getString(R.string.confirm_dialog_message), count))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onYes();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onNo();
                        }
                    }
                })
                .create();
    }

    public interface YesNoListener {
        void onYes();

        void onNo();
    }
}
