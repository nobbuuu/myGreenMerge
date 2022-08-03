package com.dream.greenmerge.dialog

import android.content.Context
import android.os.Bundle
import com.dream.greenmerge.base.BaseBindingDialog
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.databinding.DialogBindmacBinding

class BindMacDialog(
    context: Context,
    val data: List<StationInfoBean>,
    val rightOnClickBlock: ((String) -> Unit)? = null,
) : BaseBindingDialog<DialogBindmacBinding>(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.stationRv.apply {

        }
    }
}