package com.example.toss_and.util

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// divWidth, divHeight - unit: dp
class RvDecoration(val context: Context, val divWidth: Int, val divHeight: Int)
    : RecyclerView.ItemDecoration()
{
    // 좌우 간격을 divWidth로, 상하 간격을 divHeight로 맞춰준다
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        //parent.childCount
        //super.getItemOffsets(outRect, view, parent, state)
        //val pos = parent.getChildAdapterPosition(view)
        //Log.d("ABC", pos.toString())

        if (parent.childCount != state.itemCount) { // 마지막 item 제외
            if (divWidth != 0) outRect.right = convertDpToPixel(context, divWidth.toFloat())
            if (divHeight != 0) outRect.bottom = convertDpToPixel(context, divHeight.toFloat())
        }
    }
}