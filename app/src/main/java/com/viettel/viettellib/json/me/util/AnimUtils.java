package com.viettel.viettellib.json.me.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.viettel.ktts.R;

public class AnimUtils {
    public static void slide_up(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.slide_up);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void slide_down(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.slide_down);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void slide_left(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_to_left);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void slide_right(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_to_right);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void fade_in(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_in);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void fade_out(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
        if (animation != null) {
            animation.reset();
            if (view != null) {
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }
    
    public static void rotate(Context context, View view)
    {
		Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate);
		if (animation != null) {
			animation.reset();
			if (view != null) {
				view.clearAnimation();
				view.startAnimation(animation);
			}
		}
	}
}