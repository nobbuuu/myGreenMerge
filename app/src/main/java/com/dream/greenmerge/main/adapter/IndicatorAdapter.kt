package com.dream.greenmerge.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.IndicatorBean

class IndicatorAdapter : BaseQuickAdapter<IndicatorBean, BaseViewHolder>(R.layout.item_indicator) {
    override fun convert(holder: BaseViewHolder, item: IndicatorBean) {
        if (item.isCur) {
            holder.setBackgroundResource(R.id.indicatorIv, R.drawable.shape_circle_cur)
        } else {
            holder.setBackgroundResource(R.id.indicatorIv, R.drawable.shape_circle_normal)
        }
    }
}