package com.dream.greenmerge.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.DeviceInfoBean
import com.tcl.base.kt.nullToEmpty

class DeviceAdapter : BaseQuickAdapter<DeviceInfoBean, BaseViewHolder>(R.layout.item_device) {
    override fun convert(holder: BaseViewHolder, item: DeviceInfoBean) {

        holder.setText(R.id.statusTv, if (item.status == "1") "正常" else "禁用")
        holder.setText(R.id.rubbishStatus, item.ashcanSituation.nullToEmpty())
        holder.setText(R.id.recentTimeTv, item.clearTime.nullToEmpty())

    }
}