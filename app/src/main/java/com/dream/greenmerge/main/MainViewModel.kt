package com.dream.greenmerge.main

import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.net.Api
import com.tcl.base.common.BaseViewModel
import com.tcl.base.event.SingleLiveEvent

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainViewModel : BaseViewModel() {

    val unBindData = SingleLiveEvent<List<StationInfoBean>>()
    val userData = SingleLiveEvent<String>()
    fun getStationUnbindMac(id: String) {
        rxLaunchUI({
            val unbind = Api.getStationUnbindMac(id)
            unBindData.postValue(unbind)
        })
    }

    fun bindMac(id: String, mac: String) {
        rxLaunchUI({
            Api.bindMac(id, mac)
            getStationWithMac(mac)
        })
    }

    fun getStationWithMac(mac: String) {
        rxLaunchUI({
            Api.getStationWithMac(mac)
        })
    }
}