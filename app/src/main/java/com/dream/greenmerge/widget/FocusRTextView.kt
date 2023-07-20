package com.dream.greenmerge.widget

import android.content.Context
import android.util.AttributeSet
import com.blankj.utilcode.util.ColorUtils
import com.dream.greenmerge.R
import com.ruffian.library.widget.RTextView
import com.tcl.base.kt.dp

class FocusRTextView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RTextView(context, attrs) {
    init {
        helper?.apply {
            borderWidthSelected = 2.dp.toInt()
            borderColorSelected = ColorUtils.getColor(R.color.teal_700)
            borderColorPressed = ColorUtils.getColor(R.color.teal_700)
            borderWidthPressed = 2.dp.toInt()
            borderWidthNormal = 2.dp.toInt()
        }
        isFocusable = true
        isFocusableInTouchMode = true
    }
}