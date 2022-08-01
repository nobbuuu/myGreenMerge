package com.dream.greenmerge.main

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dream.greenmerge.bean.IndicatorBean
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.common.MmkvConstant.KEY_USER_PROJECT_ID
import com.dream.greenmerge.databinding.ActivityMainBinding
import com.dream.greenmerge.main.adapter.DeviceAdapter
import com.dream.greenmerge.main.adapter.IndicatorAdapter
import com.tcl.base.common.ui.BaseActivity
import com.tcl.base.utils.MmkvUtil
import com.tcl.base.weiget.recylerview.WaterFallItemDecoration

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    val mDeviceAdapter = DeviceAdapter()
    val mIndicatorAdapter = IndicatorAdapter()
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        mBinding.deviceRv.apply {
            adapter = mDeviceAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(WaterFallItemDecoration(20, 20))
        }

        val indicatorList = arrayListOf<IndicatorBean>()
        repeat(4) {
            indicatorList.add(IndicatorBean(it == 0))
        }
        mBinding.indicatorRv.apply {
            adapter = mIndicatorAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            addItemDecoration(WaterFallItemDecoration(18, 0))
        }
        mIndicatorAdapter.setList(indicatorList)

        MmkvUtil.decodeString(KEY_USER_PROJECT_ID)?.let {
            viewModel.getStationUnbindMac(it)
        }
    }

    override fun initDataOnResume() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.unBindData.observe(this){
            mDeviceAdapter.setList(it)
        }
    }
}