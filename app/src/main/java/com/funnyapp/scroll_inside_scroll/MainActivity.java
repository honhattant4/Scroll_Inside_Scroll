package com.funnyapp.scroll_inside_scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements ScrollChild.PullUpDownListerning {
    ScrollView mScrollParent;
    ScrollChild mScrollChild;
    int heightScrollChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //commit1
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollParent = (ScrollView) findViewById(R.id.scrollParent);
        mScrollChild = (ScrollChild) findViewById(R.id.scrollChild);
        hanlderScroll();
    }

    private void hanlderScroll() {
        mScrollParent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View p_v, MotionEvent p_event) {
                mScrollChild.getParent().requestDisallowInterceptTouchEvent(false);
                //  We will have to follow above for all scrollable contents
                return false;
            }
        });


        mScrollChild.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View p_v, MotionEvent p_event) {
                // this will disallow the touch request for parent scroll on touch of child view
                boolean top = false;
                boolean botton = false;
                if (p_event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mScrollChild.getScrollY() == 0) {
                        top = true;
                    }
                    if (mScrollChild.getScrollY() == 530) {
                        botton = true;
                    }
                }
                if (p_event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (mScrollChild.getScrollY() > 0) {
                        if (top) {
                            p_v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                    if (mScrollChild.getScrollY() < 530) {
                        if (botton) {
                            p_v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }
                p_v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mScrollChild.setOnEventListener(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        heightScrollChild = 530;
    }


    @Override
    public void callbackEnable() {
        mScrollChild.getParent().requestDisallowInterceptTouchEvent(false);
    }

    @Override
    public void callbackDisable() {
        mScrollChild.getParent().requestDisallowInterceptTouchEvent(true);
    }
}
