package com.dream.greenmerge.dialog

import android.content.Context
import android.os.Bundle
import com.dream.greenmerge.base.BaseBindingDialog
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.databinding.DialogBindmacBinding
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.nullToEmpty

class BindMacDialog(
    context: Context,
    val data: List<StationInfoBean>,
    val rightOnClickBlock: ((String) -> Unit)? = null,
) : BaseBindingDialog<DialogBindmacBinding>(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.stationRv.setData(data)

        mBinding.sureTv.ktClick {
            dismiss()
            mBinding.stationRv.getSelect()?.let { select ->
                val id = data.find { it.name == select.nameStr }?.id
                rightOnClickBlock?.invoke(id.nullToEmpty())
            }
        }

        mBinding.cancelTv.ktClick {
            dismiss()
        }
    }
}