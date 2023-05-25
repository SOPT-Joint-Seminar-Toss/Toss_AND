package com.example.toss_and.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Window
import android.view.WindowManager

fun setStatusBarColor(activity: Activity, color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity.applicationContext.getColor(color)
    }
}

fun convertPixelToDp(context: Context, px: Int): Int {
    val metrics = context.resources.displayMetrics
    return px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun convertDpToPixel(context: Context, dp: Float): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics).toInt()
}
