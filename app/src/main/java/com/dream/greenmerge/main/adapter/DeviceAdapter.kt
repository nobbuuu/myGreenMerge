package com.dream.greenmerge.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.StationInfoBean

class DeviceAdapter : BaseQuickAdapter<StationInfoBean, BaseViewHolder>(R.layout.item_device) {
    override fun convert(holder: BaseViewHolder, item: StationInfoBean) {

        holder.setText(R.id.deviceStatus,if (item.type == "1") "正常" else "异常")
        holder.setText(R.id.rubbishStatus,item.ashcanSituation)
        holder.setText(R.id.recentTimeTv,item.cleanTime)

    }
}