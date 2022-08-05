package com.dream.greenmerge.main

import com.dream.greenmerge.bean.DeviceInfoBean
import com.dream.greenmerge.bean.DevicePageBean
import com.dream.greenmerge.bean.StationDetailBean
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.net.Api
import com.tcl.base.common.BaseViewModel
import com.tcl.base.event.SingleLiveEvent
import com.tcl.base.kt.ktToastShow

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainViewModel : BaseViewModel() {

    val unBindData = SingleLiveEvent<List<StationInfoBean>>()
    val deviceData = SingleLiveEvent<DevicePageBean>()
    val stationDetailResult = SingleLiveEvent<StationDetailBean>()
    val userData = SingleLiveEvent<String>()
    val isBindMac = SingleLiveEvent<Boolean>()
    fun getStationUnbindMac(id: String) {
        rxLaunchUI({
            val unbind = Api.getStationUnbindMac(id)
            unBindData.postValue(unbind)
        })
    }

    fun bindMac(id: String, mac: String) {
        rxLaunchUI({
            Api.bindMac(id, mac)
            "绑定成功".ktToastShow()
            getStationWithMac(mac)
        })
    }

    fun getStationWithMac(mac: String) {
        rxLaunchUI({
            val result = Api.getStationWithMac(mac)
            stationDetailResult.postValue(result)
        })
    }

    fun isBindMac(mac: String) {
        rxLaunchUI({
            val result = Api.isBindMac(mac)
            isBindMac.postValue(result)
        })
    }

    fun getDeviceList(pageNo: Int, site: String) {
        rxLaunchUI({
            val result = Api.getDeviceList(pageNo,site)
            deviceData.postValue(result)
        })
    }
}