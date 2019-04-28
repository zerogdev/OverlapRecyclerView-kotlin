package com.zerogdev.library

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet


class OverlapRecyclerView : RecyclerView {

    var selectedPosition = 0
    set(value) {
        field = value
        adapter?.notifyDataSetChanged()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }


    fun init() {
        isChildrenDrawingOrderEnabled = true
    }

    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
        if (layoutManager is LinearLayoutManager) {
            var selectedPosition = this.selectedPosition - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (selectedPosition < 0 || i < selectedPosition) {
                return i
            } else {
                if (i == childCount - 1) {
                    return selectedPosition
                } else if (i >= selectedPosition) {
                    return i + 1
                } else {
                    return i
                }
            }
        }
        return i
    }
}

