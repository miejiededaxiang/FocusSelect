package com.htl.recyclerfocucedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Juwan on 16/12/9.
 */
public class CustomeLayoutManager extends RecyclerView.LayoutManager {

    private final static String TAG = CustomeLayoutManager.class.getSimpleName();
    private final static boolean DEBUG = true;

    final static class LayoutParams extends RecyclerView.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (DEBUG) Log.i(TAG, "generateDefaultLayoutParams: ");
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (DEBUG) Log.d(TAG, "onLayoutChildren: ");
        super.onLayoutChildren(recycler, state);
    }
}
