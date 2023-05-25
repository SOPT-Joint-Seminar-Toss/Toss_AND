package com.example.toss_and.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

fun setStatusBarColor(activity: Activity, color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity.applicationContext.getColor(color)
    }
}
