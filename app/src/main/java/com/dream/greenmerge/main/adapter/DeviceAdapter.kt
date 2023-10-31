package com.dream.greenmerge.main.adapter

import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.DeviceInfoBean
import com.tcl.base.kt.nullToEmpty
import java.text.SimpleDateFormat
import java.util.Locale

class DeviceAdapter : BaseQuickAdapter<DeviceInfoBean, BaseViewHolder>(R.layout.item_device) {
    override fun convert(holder: BaseViewHolder, item: DeviceInfoBean) {

        holder.setText(R.id.statusTv, if (item.deviceStatus == "0") "正常" else "异常")
        holder.setText(R.id.rubbishStatus, item.ashcanSituation.nullToEmpty())
        holder.setText(R.id.deviceName, item.name.nullToEmpty())
        item.clearTime?.let {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = dateFormat.parse(it)
            val clearTime = TimeUtils.millis2String(date.time, "yyyy-MM-dd HH:mm")
            holder.setText(R.id.recentTimeTv, clearTime)
        }

    }
}