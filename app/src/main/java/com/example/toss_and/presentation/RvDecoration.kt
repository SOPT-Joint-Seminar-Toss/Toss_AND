package com.example.toss_and.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RvDecoration(val divWidth: Int): RecyclerView.ItemDecoration() {
    // 좌우 간격을 divWidth로 맞춰준다
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = divWidth
    }
}