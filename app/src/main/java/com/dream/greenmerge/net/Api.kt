package com.dream.greenmerge.net

import UserInfoBean
import com.dream.greenmerge.bean.DevicePageBean
import com.dream.greenmerge.bean.StationDetailBean
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.common.MmkvConstant.KEY_ACCESS_TOKEN
import com.dream.greenmerge.net.parser.Response
import com.tcl.base.utils.MmkvUtil
import rxhttp.toStr
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse
import rxhttp.wrapper.param.toResponseReturn

/**
 * @author : tiaozi
 * time : 2020/11/9 17:30
 */
object Api {

    /**
     * 登录
     */
    suspend fun login(username: String, password: String): String {
        return RxHttp.postJson("/login")
            .setAssemblyEnabled(false)
            .add("username", username)
            .add("password", password)
            .add("type", "1")
            .toStr()
            .await()
    }

    /**
     * 获取用户信息
     */
    suspend fun getUserInfo(): String {
        return RxHttp.get("/getInfo")
            .addHeader("Authorization", MmkvUtil.decodeString(KEY_ACCESS_TOKEN))
            .toStr()
            .await()
    }

    /**
     * 获取项目下未绑定MAC的站点
     */
    suspend fun getStationUnbindMac(id: String): List<StationInfoBean> {
        return RxHttp.get("/lh/lhSiteMgt/siteListToProject")
            .add("id", id)
            .toResponse<List<StationInfoBean>>()
            .await()
    }

    /**
     * 站点绑定MAC地址
     */
    suspend fun bindMac(id: String, mac: String): String {
        return RxHttp.putJson("/lh/lhSiteMgt/edit")
            .add("id", id)
            .add("mac", mac)
            .toStr()
            .await()
    }

    /**
     * 根据mac地址获取站点详情
     */
    suspend fun getStationWithMac(mac: String): StationDetailBean {
        return RxHttp.get("/lh/lhSiteMgt/siteInfo")
            .add("mac", mac)
            .toResponse<StationDetailBean>()
            .await()
    }

    /**
     * MAC是否绑定站点
     */
    suspend fun isBindMac(mac: String): Boolean {
        return RxHttp.get("/lh/lhSiteMgt/isItBound")
            .add("mac", mac)
            .toResponse<Boolean>()
            .await()
    }

    /**
     * 分页查询设备列表
     */
    suspend fun getDeviceList(pageNo: Int, site: String): DevicePageBean {
        return RxHttp.get("/lh/lhEqpMgt/list")
            .add("pageNo", pageNo)
            .add("pageSize", 5)
            .add("site", site)
            .toResponse<DevicePageBean>()
            .await()
    }

}