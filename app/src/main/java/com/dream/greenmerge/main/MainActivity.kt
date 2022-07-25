package com.dream.greenmerge.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.dream.greenmerge.databinding.ActivityMainBinding
import com.tcl.base.common.ui.BaseActivity

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(){

    override fun initStateBar(stateBarColor: Int, isLightMode: Boolean, fakeView: View?) {
        BarUtils.setStatusBarLightMode(this, true)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    /**监听新的intent*/
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun initData() {

    }

    override fun initDataOnResume() {

    }
}