package com.devwu.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by WuNan-QF on 16/5/28.
 */
public class AnimationUtil {
    public static void shakeX(View view, float bounds) {
        shake(view, bounds, Direction.X);
    }

    public static void shakeY(View view, float bounds) {
        shake(view, bounds, Direction.Y);
    }

    private static void shake(View view, float bounds, Direction direction) {
        TranslateAnimation animation = null;
        switch (direction) {
            case X:
                animation = new TranslateAnimation(+bounds, -bounds, 0, 0);
                break;
            case Y:
                animation = new TranslateAnimation(0, 0, +bounds, -bounds);
                break;
        }
        if (animation != null) {
            animation.setInterpolator(new OvershootInterpolator());
            animation.setDuration(30);
            animation.setRepeatCount(6);
            animation.setRepeatMode(Animation.REVERSE);
            view.startAnimation(animation);
        }
    }

    public enum Direction {
        X(0),//横向
        Y(1);//纵向

        final int direction;

        Direction(int direction) {
            this.direction = direction;
        }
    }
}
