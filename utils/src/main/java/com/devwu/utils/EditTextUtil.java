package com.devwu.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by WuNan-QF on 16/7/14.
 */
public class EditTextUtil {
    public static String getString(EditText editText) {
        if (editText == null){
            return "";
        }
        return editText.getText().toString();
    }

    public static void hideKeyboard(Activity context){
        hideKeyboard(context, ViewUtil.getRootView(context));
    }

    public static void hideKeyboard(Context context,View rootView){
        InputMethodManager imm = (InputMethodManager) rootView.getContext().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(rootView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
