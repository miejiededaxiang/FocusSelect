package com.htl.recyclerfocucedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * Created by Juwan on 16/11/24.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.LinearViewHolder> {

    private static final String TAG = HorizontalAdapter.class.getSimpleName();
    private static final boolean DEBUG = true;

    private static final int INVALID_POSITION = -1;

    private static List<Integer> mContents;
    private Context mContext;

    private Callback mCallback;

    private int mCurrentFocusPosition = INVALID_POSITION;
    private int mSelectedPosition = INVALID_POSITION;

    public HorizontalAdapter(Context context) {
        mContext = context;
        generateTestData();
    }

    public void addCallback(@NonNull Callback callback) {
        mCallback = callback;
    }

    public void disableAllItemFocusable() {
    }

    public void selectItem(int position) {
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    public static List<Integer> generateTestData() {
        final int max = 40;
        mContents = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            mContents.add(i);
        }

        return mContents;
    }

    @Override
    public LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(LinearViewHolder holder, int position) {
        holder.mNumView.setText(mContents.get(position)+"");

        if (position == mSelectedPosition) {
            holder.itemView.setSelected(true);
        } else {
            holder.itemView.setSelected(false);
        }

        if (position == mCurrentFocusPosition) {
            holder.itemView.requestFocus();
        }
    }

    @Override
    public int getItemCount() {
        return mContents != null ? mContents.size() : 0;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_num) TextView mNumView;

        public LinearViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnFocusChange(R.id.tv_num)
        public void focusItem(boolean hasFocus) {
            if (hasFocus) {
                mCurrentFocusPosition = getAdapterPosition();
            }
        }

        @OnClick(R.id.tv_num)
        public void clickItem() {
            if (mCallback != null) {
                mCallback.onItemClicked(getAdapterPosition());
            }
        }
    }

    public interface Callback {
        void onItemClicked(int position);
    }
}
