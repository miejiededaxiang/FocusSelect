package com.android.wheelselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemSelectListener {
    private MyAdapter mAdapter;
    int[] mInts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private android.widget.TextView tvage;
    private RecyclerView recyclerView;

    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();


    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        //让recyclerview横向滚动
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        CustomLayoutManager manager = new CustomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MyAdapter(this, mInts);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemSelectListener(this);



//        //得到屏幕的中间位置
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    //将位置移动到中间位置
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(getScrollPosition(), 0);
                    System.out.println(getScrollPosition() + "");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }
        });
    }

//    /**
//     * 获取中间位置的position
//     *
//     * @return
//     */
//    private int getMiddlePosition() {
//        return getScrollPosition();
//    }

    /**
     * 获取滑动值, 滑动偏移 / 每个格子宽度
     *即当前item的位置
     * @return 当前值
     */
    private int getScrollPosition() {
        int firstitem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        Log.d("test",firstitem+"++++++++");
        return (int) (((double) recyclerView.computeHorizontalScrollOffset()
                / (double) mAdapter.getItemWidth(recyclerView)) + 0.5f);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvage = (TextView) findViewById(R.id.tv_age);
    }

    @Override
    public void onItemSelect(View view, int position) {
        this.position = position%17;
////        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(getScrollPosition(), 0);
//
       int firstitem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        Log.d("test",position+"");
        Log.d("test",firstitem+"=======");
        if(position>firstitem){
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position,0);
        }else{
//            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position+1,0);
        }
//        Toast.makeText(MainActivity.this, "=============", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        View curFocusView = getWindow().getDecorView().findFocus();
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
            if(curFocusView.getId() == R.id.btn_2){
                LinearLayout childAt = (LinearLayout) ((LinearLayoutManager) recyclerView.getLayoutManager()).findViewByPosition(position);
//                Log.e("Test", "onKeyDown: "+ firstItem%17);
//                = (LinearLayout) recyclerView.getChildAt(position);

                Button bu = (Button) childAt.getChildAt(0);
                bu.setFocusable(true);
                bu.requestFocus();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}

