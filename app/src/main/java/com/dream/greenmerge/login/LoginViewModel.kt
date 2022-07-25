package com.dream.greenmerge.login

import com.dream.greenmerge.common.MmkvConstant.KEY_ACCESS_TOKEN
import com.dream.greenmerge.net.Api
import com.tcl.base.common.BaseViewModel
import com.tcl.base.event.SingleLiveEvent
import com.tcl.base.utils.MmkvUtil

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class LoginViewModel : BaseViewModel() {
    val loginResult = SingleLiveEvent<String>()
    fun login(username: String, password: String) {
        rxLaunchUI({
            val result = Api.login(username, password)
            result?.let {
                loginResult.postValue(it)
                MmkvUtil.encode(KEY_ACCESS_TOKEN, it)
            }
        })
    }

}