package com.dream.greenmerge.utils

import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ScreenUtils
import com.dream.greenmerge.R
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktToColor

object DialogHelp {

/**
 * 显示通用的选择dialog
 */
fun showTwoBottomDialog(
    contentSpanned: Spanned? = null,
    contentStr: String,
    leftTextStr: String = "取消",
    rightTextStr: String = "确定",
    centreTitle: String = "",
    leftTextColor: Int = R.color.color_4A556B.ktToColor,
    rightTextColor: Int = R.color.color_4A556B.ktToColor,
    leftOnClickBlock: (() -> Unit)? = null,
    rightOnClickBlock: (() -> Unit)? = null,
    dismissLister: (() -> Unit)? = null,
    cancelable: Boolean = true
): AlertDialog {
    return  showBottomDialog(
        contentSpanned,
        contentStr,
        leftTextStr,
        rightTextStr,
        centreTitle,
        leftTextColor,
        rightTextColor,
        leftOnClickBlock,
        rightOnClickBlock,
        dismissLister,
        cancelable,
        R.layout.dialog_common_confirm)
}

/**
 * 显示通用的选择dialog
 */
fun showBottomDialog(
    contentSpanned: Spanned?,
    contentStr: String,
    leftTextStr: String = "取消",
    rightTextStr: String = "确定",
    centreTitle: String,
    leftTextColor: Int,
    rightTextColor: Int,
    leftOnClickBlock: (() -> Unit)?,
    rightOnClickBlock: (() -> Unit)?,
    dismissLister: (() -> Unit)?,
    cancelable: Boolean,
    layouy:Int
): AlertDialog {
    val inflate =
        LayoutInflater.from(ActivityUtils.getTopActivity())
            .inflate(layouy, null)
    return  showBottomDialog(
        contentSpanned,
        contentStr,
        leftTextStr,
        rightTextStr,
        centreTitle,
        leftTextColor,
        rightTextColor,
        leftOnClickBlock,
        rightOnClickBlock,
        dismissLister,
        cancelable,
        inflate)
}
/**
 * 显示通用的选择dialog
 */
fun showBottomDialog(
    contentSpanned: Spanned? = null,
    contentStr: String = "",
    leftTextStr: String = "取消",
    rightTextStr: String = "确定",
    centreTitle: String = "",
    leftTextColor: Int = R.color.color_4A556B.ktToColor,
    rightTextColor: Int = R.color.white.ktToColor,
    leftOnClickBlock: (() -> Unit)? = null,
    rightOnClickBlock: (() -> Unit)? = null,
    dismissLister: (() -> Unit)? = null,
    cancelable: Boolean = true,
    view:View
): AlertDialog {
    val builder = AlertDialog.Builder(ActivityUtils.getTopActivity(), R.style.dialogBase)
    builder.setView(view)
    val dialog = builder.create()
    dialog.setOnDismissListener { dismissLister?.invoke() }
    val tvContent = view.findViewById<TextView>(R.id.tv_content)
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    dialog.setCancelable(cancelable)
    if (centreTitle.isEmpty()) {
        tvTitle?.visibility = View.GONE
    } else {
        tvTitle?.text = centreTitle
        tvTitle?.visibility = View.VISIBLE
    }
    tvContent?.run {
        if (contentStr.isEmpty()) {
            tvContent?.text = contentSpanned
        } else {
            tvContent?.text = contentStr
        }
    }


    view.findViewById<TextView>(R.id.tv_cancel)?.run {
        text = leftTextStr
        setTextColor(leftTextColor)
        if (leftOnClickBlock == null) {
            ktClick { dialog.dismiss() }
        } else {
            ktClick {
                leftOnClickBlock.invoke()
                dialog.dismiss()
            }
        }
    }

    view.findViewById<TextView>(R.id.tv_confirm)?.run {
        text = rightTextStr
        setTextColor(rightTextColor)
        ktClick { }
        if (rightOnClickBlock == null) {
            ktClick { dialog.dismiss() }
        } else {
            ktClick {
                rightOnClickBlock.invoke()
                dialog.dismiss()
            }
        }
    }

    dialog.show()
    //这个要放在show 之后,才能生效
    dialog.window?.setLayout(
        (ScreenUtils.getAppScreenWidth() * 0.8).toInt(),
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    return dialog
} }
