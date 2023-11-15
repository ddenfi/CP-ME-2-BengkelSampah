package com.bengkelsampah.bengkelsampahapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Class ini digunakan untuk mengatur margin pada item Recycler View
 */
class MarginItemDecoration(
    private val verticalSpaceSize: Int,
    private val horizontalSpaceSize: Int,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = verticalSpaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = horizontalSpaceSize
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = horizontalSpaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = verticalSpaceSize
                }
            }
            right = horizontalSpaceSize
            bottom = verticalSpaceSize
        }
    }
}