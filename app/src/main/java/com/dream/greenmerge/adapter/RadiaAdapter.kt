package com.dream.greenmerge.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dream.greenmerge.R
import com.dream.greenmerge.bean.SelectInfoBean
import com.ruffian.library.widget.RCheckBox
import com.tcl.base.kt.ktClick

class RadiaAdapter :
    BaseQuickAdapter<SelectInfoBean, BaseViewHolder>(R.layout.item_select_view) {
    override fun convert(holder: BaseViewHolder, item: SelectInfoBean) {
        val itemTv = holder.getView<RCheckBox>(R.id.selectTv)
        itemTv.text = item.nameStr
        itemTv.isChecked = item.isCheck
        itemTv.ktClick {
            data.forEach {
                if (it.nameStr == item.nameStr) {
                    it.isCheck = !it.isCheck
                } else {
                    it.isCheck = false
                }
            }
            notifyDataSetChanged()
        }
    }
}