package com.dream.greenmerge.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.DeviceInfoBean
import com.tcl.base.kt.nullToEmpty

class DeviceItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_device, this)
    }

    fun setData(item: DeviceInfoBean) {
        val statusTv = findViewById<TextView>(R.id.statusTv)
        val rubbishStatus = findViewById<TextView>(R.id.rubbishStatus)
        val recentTimeTv = findViewById<TextView>(R.id.recentTimeTv)
        statusTv.text = if (item.status == "1") "正常" else "禁用"
        rubbishStatus.text = item.ashcanSituation.nullToEmpty()
        recentTimeTv.text = item.clearTime.nullToEmpty()
    }
}