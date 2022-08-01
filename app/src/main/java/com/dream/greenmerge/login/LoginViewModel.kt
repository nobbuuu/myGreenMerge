package com.dream.greenmerge.login

import UserInfoBean
import com.blankj.utilcode.util.GsonUtils
import com.dream.greenmerge.common.MmkvConstant
import com.dream.greenmerge.common.MmkvConstant.KEY_ACCESS_TOKEN
import com.dream.greenmerge.net.Api
import com.tcl.base.common.BaseViewModel
import com.tcl.base.event.SingleLiveEvent
import com.tcl.base.kt.nullToEmpty
import com.tcl.base.utils.MmkvUtil

/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class LoginViewModel : BaseViewModel() {
    val loginResult = SingleLiveEvent<Boolean>()
    fun login(username: String, password: String) {
        rxLaunchUI({
            val result = Api.login(username, password)
            MmkvUtil.encode(KEY_ACCESS_TOKEN, result)
            getUserInfo()
        })
    }

    fun getUserInfo() {
        val token = MmkvUtil.decodeString(MmkvConstant.KEY_ACCESS_TOKEN)
        rxLaunchUI({
            val userInfo = Api.getUserInfo(token.nullToEmpty())
            val info = GsonUtils.fromJson(userInfo, UserInfoBean::class.java)
            MmkvUtil.encode(MmkvConstant.KEY_USER_PROJECT_ID, info?.user?.projectId.nullToEmpty())
            loginResult.postValue(true)
        })
    }

}