package com.areaonline.utils;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.areaonline.R;

public class FloatingView {

    /* Floating view is used to display a custom view for attachments in the chat screen */

    private static PopupWindow popWindow;

    public static void onShowPopup(LinearLayout mSwipeRefreshLayout, Activity activity, View inflatedView) {

        // get device size
        Display display = activity.getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        // fill the data to the list items
        // set height depends on the device size
        popWindow = new PopupWindow(inflatedView, size.x, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // set a background drawable with rounders corners
        popWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.color.white));
        // make it focusable to show the keyboard to enter in `EditText`
        popWindow.setFocusable(true);
        // make it outside touchable to dismiss the popup window
        popWindow.setOutsideTouchable(true);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // show the popup at bottom of the screen and set some margin at
        // bottom ie,
        if (activity.getCurrentFocus() != null){
            popWindow.showAtLocation(activity.getCurrentFocus(), Gravity.BOTTOM, 0,
                    0);
        }else{
            popWindow.showAtLocation(mSwipeRefreshLayout, Gravity.BOTTOM, 0,
                    200);
        }

    }

    public static void dismissWindow() {

        popWindow.dismiss();
    }
}