package com.github.mvpstatelibexample.ui.adapters.fourth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.mvp.models.beans.fourth.ApiConvertedModel;
import com.github.mvpstatelibexample.ui.adapters.common.AbsRecyclerAdapter;

/**
 * Created by grishberg on 23.04.17.
 */

public class LeftItemsAdapter extends AbsRecyclerAdapter<ApiConvertedModel> {
    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_left, parent, false));
    }

    private static class ViewHolder extends BaseRecyclerHolder<ApiConvertedModel> {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.listItemText);
        }

        @Override
        public void bind(ApiConvertedModel item) {
            super.bind(item);
            textView.setText(item.getName());
        }
    }
}
