package com.dream.greenmerge.utils

import android.app.Activity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.dream.greenmerge.R

class StatusBarUtils {
    fun adjustWindow(activity: Activity, isLight: Boolean) {
        if (isLight) {
            BarUtils.setStatusBarColor(activity, ColorUtils.getColor(R.color.white))
        } else {
            BarUtils.setStatusBarColor(activity, ColorUtils.getColor(R.color.transparent))
        }
        BarUtils.setStatusBarLightMode(activity, isLight)
    }
}