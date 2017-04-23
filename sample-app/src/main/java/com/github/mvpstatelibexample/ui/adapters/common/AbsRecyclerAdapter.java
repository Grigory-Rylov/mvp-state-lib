package com.github.mvpstatelibexample.ui.adapters.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by grishberg on 23.04.17.
 */

public abstract class AbsRecyclerAdapter<T> extends RecyclerView.Adapter<AbsRecyclerAdapter.BaseRecyclerHolder<T>> {

    private List<T> items;

    public void setItems(final List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder<T> holder, int position) {
        if (items == null) {
            return;
        }
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class BaseRecyclerHolder<I> extends RecyclerView.ViewHolder {
        private I payload;

        public BaseRecyclerHolder(View itemView) {
            super(itemView);
        }

        public void bind(I item) {
            this.payload = item;
        }
    }
}
