package com.bengkelsampah.bengkelsampahapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Class ini digunakan untuk mengatur margin pada item Recycler View
 */
class MarginItemDecoration(
    private val verticalSpaceSize: Int,
    private val horizontalSpaceSize: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = verticalSpaceSize
            }
            left = horizontalSpaceSize
            right = horizontalSpaceSize
            bottom = verticalSpaceSize
        }
    }
}