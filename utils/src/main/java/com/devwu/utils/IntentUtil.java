package com.devwu.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by WuNan on 17/5/17.
 */

public class IntentUtil {
    public static void callPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
