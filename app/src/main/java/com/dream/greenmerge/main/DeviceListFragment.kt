package com.dream.greenmerge.main

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.dream.greenmerge.bean.DeviceInfoBean
import com.dream.greenmerge.databinding.FragmentDeviceListBinding
import com.dream.greenmerge.main.adapter.DeviceAdapter
import com.tcl.base.common.ui.BaseFragment
import com.tcl.base.weiget.recylerview.WaterFallItemDecoration

class DeviceListFragment(val site: String, val page: Int) :
    BaseFragment<MainViewModel, FragmentDeviceListBinding>() {
    val mDeviceAdapter = DeviceAdapter()
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.deviceRv.apply {
            adapter = mDeviceAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(WaterFallItemDecoration(20, 20))
        }
        viewModel.getDeviceList(page, site)
    }

    fun setData(list: List<DeviceInfoBean>) {
        mDeviceAdapter.setList(list)
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.deviceData.observe(this) {
            mDeviceAdapter.setList(it.records)
        }
    }
}