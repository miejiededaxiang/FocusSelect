package com.android.wheelselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by wu on 2015/12/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.AgeItemViewHolder> {

    private Context mContext;

    /**
     * 显示的数据个数
     */
    public int ITEM_NUM = 7;
    /**
     * 当前选中位置，高亮显示
     */
    int[] mInts;

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    private OnItemSelectListener mSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener listener){
        mSelectListener = listener;
    }

    public MyAdapter(Context context, int[] ints) {
        mContext = context;
        this.mInts = ints;
    }

    @Override
    public AgeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_age_item, parent, false);
        //设置宽度
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = getItemWidth(parent);


        return new AgeItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AgeItemViewHolder holder, final int position) {
        holder.mButton.setText(mInts[position % 17] + "");
        holder.mButton.setTag(position);

        holder.mButton.setTextSize(20);
        holder.mButton.setFocusable(true);
        holder.mButton.setBackgroundResource(R.drawable.fds);

        holder.mButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    int currentPosition = (int) view.getTag();
                    Log.d("test","adapter=position==="+position);
                    Log.d("test","adapter=currentPosition==="+currentPosition);
                    mSelectListener.onItemSelect(view, currentPosition);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 获取每一个条目的宽度
     *
     * @return
     */
    public int getItemWidth(View view) {
        int with = view.getMeasuredWidth() / ITEM_NUM;
        return with;
    }

    /**
     * ViewHolder类
     */
    public class AgeItemViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public AgeItemViewHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.tv_item_age);
        }
    }
}
