package com.android.wheelselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;


/**
 * author songwenju
 * email：songwenju@outlook.com
 * 针对电视的自定义的RecyclerView，该RecyclerView具有以下功能：
 * 1.响应五向键，上下左右会跟着移动，并获得焦点，在获得焦点时会抬高
 * 2.在鼠标hover在条目上时会获得焦点。
 * 3.添加了条目的点击和长按事件
 * 4.添加了是否第一个可见条目和是否是最后一个可见条目的方法
 * 5.在item获得焦点时和失去焦点时，这里有相应的回调方法。
 */
public class CustomRecyclerView extends RecyclerView {

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //在recyclerView的move事件情况下，拦截调，只让它响应五向键和左右箭头移动
        return ev.getAction() == MotionEvent.ACTION_MOVE || super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int dx = this.getChildAt(0).getWidth();
        View focusView = this.getFocusedChild();
        if (focusView != null) {
            //处理左右方向键移动Item到边之后RecyclerView跟着移动
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        return true;
                    } else {
                        this.smoothScrollBy(dx, 0);
                        if (focusView != null) {
                            if (dx > 0) {
                                View rightView = FocusFinder.getInstance().findNextFocus(this, focusView, View.FOCUS_RIGHT);
                                if (rightView != null) {
                                    rightView.requestFocusFromTouch();
                                }
                            } else {
                                View rightView = FocusFinder.getInstance().findNextFocus(this, focusView, View.FOCUS_LEFT);
                                if (rightView != null) {
                                    rightView.requestFocusFromTouch();
                                }
                            }
                        }
                        //移动之后获得焦点，是在scroll方法中处理的。
                        return true;
//                        View rightView = FocusFinder.getInstance().findNextFocus(this, focusView, View.FOCUS_RIGHT);
//                        LogUtil.i(this, "rightView is null:" + (rightView == null));
//                        if (rightView != null) {
//                            LogUtil.i(this, "CustomRecyclerView.requestFocusFromTouch.");
//                            rightView.requestFocusFromTouch();
//                            return true;
//                        } else {
//                            this.smoothScrollBy(dx, 0);
//                            //移动之后获得焦点，是在scroll方法中处理的。
//                            return true;
//                        }
                    }
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    View leftView = FocusFinder.getInstance().findNextFocus(this, focusView, View.FOCUS_LEFT);
//                    LogUtil.i(this, "left is null:" + (leftView == null));
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        return true;
                    } else {
                        if (leftView != null) {
                            leftView.requestFocusFromTouch();
                            return true;
                        } else {
                            this.smoothScrollBy(-dx, 0);
                            return true;
                        }
                    }
            }
        }
        return super.dispatchKeyEvent(event);
    }


}
