package com.funnyapp.scroll_inside_scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by honhattan on 12/9/15.
 */
public class ScrollChild extends ScrollView {
    private static final String TAG = "ScrollChild";
    PullUpDownListerning pullUpDownListerning;
    int height;

    public ScrollChild(Context context) {
        super(context);
    }

    public ScrollChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG, "l=" + l);
        Log.d(TAG, "t=" + t);
        Log.d(TAG, "oldl=" + oldl);
        Log.d(TAG, "oldt=" + oldt);
        if (t == height) {
            if (pullUpDownListerning != null)
                pullUpDownListerning.callbackEnable();
        } else if (t == 0) {
            if (pullUpDownListerning != null)
                pullUpDownListerning.callbackEnable();
        } else {
            if (pullUpDownListerning != null)
                pullUpDownListerning.callbackDisable();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        height = 530;

    }

    public void setOnEventListener(PullUpDownListerning listener) {
        pullUpDownListerning = listener;
    }

    public interface PullUpDownListerning {
        //pull=true pull down oldY<Y
        //pull=false pull up
        public void callbackEnable();

        public void callbackDisable();
    }
}
