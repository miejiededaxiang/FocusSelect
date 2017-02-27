package com.htl.recyclerfocucedemo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

/**
 * debug用的调试工具.
 * Created by Juwan on 16/6/22.
 */
public class DebugUtil {
    private static final String TAG = DebugUtil.class.getSimpleName();

    /**
     * 打印当前activity焦点位置.
     * @param activity 当前activity.
     * @param tag 打印显示的tag.
     */
    public static void logFocus(@NonNull Activity activity, @NonNull String tag) {
        View focusView = activity.getCurrentFocus();
        if (focusView != null) {
            Log.d(tag, "focus view is " + focusView.toString());
        } else {
            Log.e(tag, "focusView is null!");
        }
        activity = null;
    }
}
