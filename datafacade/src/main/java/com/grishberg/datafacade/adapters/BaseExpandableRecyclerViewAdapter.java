package com.github.datafacade.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.datafacade.ListResultCloseable;
import com.github.datafacade.viewholder.BaseViewHolder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grishberg on 01.01.17.
 */
public abstract class BaseExandableRecyclerViewAdapter<T1, T2>
        extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = BaseExandableRecyclerViewAdapter.class.getSimpleName();

    @Nullable
    private ListResultCloseable<T1> items;
    private final Map<T1, ListResultCloseable<T2>> subitems;

    public BaseExandableRecyclerViewAdapter() {
        subitems = new ConcurrentHashMap<>();
    }

    public void recycle() {
        if (subitems != null) {
            try {
                for (Map.Entry<T1, ListResultCloseable<T2>> entry : subitems.entrySet()) {
                    entry.getValue().close();
                }
            } catch (final IOException e) {
                Log.e(TAG, "recycle: ", e);
            }
        }
        if (items != null) {
            try {
                items.close();
            } catch (final IOException e) {
            }
        }
    }

    public void onSubitemsReceived(@NonNull final T1 category, final ListResultCloseable<T2> subitemsForCategory) {
        subitems.put(category, subitemsForCategory);
        notifyDataSetChanged();
    }

    protected abstract void onClickedCategoryItem(T1 category);

    protected abstract void onClickedSubitem(T2 item);
}
