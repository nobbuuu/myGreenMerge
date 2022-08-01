package com.dream.greenmerge.net

import UserInfoBean
import com.dream.greenmerge.bean.DevicePageBean
import com.dream.greenmerge.bean.StationDetailBean
import com.dream.greenmerge.bean.StationInfoBean
import com.dream.greenmerge.net.parser.Response
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
            .toResponse<String>()
            .await()
    }

    /**
     * 获取用户信息
     */
    suspend fun getUserInfo(token: String): String {
        return RxHttp.postForm("/getInfo")
            .setAssemblyEnabled(false)
            .add("TOKEN", token)
            .toStr()
            .await()
    }

    /**
     * 获取项目下未绑定MAC的站点
     */
    suspend fun getStationUnbindMac(id: String): List<StationInfoBean> {
        return RxHttp.postForm("/lh/lhSiteMgt/siteListToProject")
            .setAssemblyEnabled(false)
            .add("id", id)
            .toResponse<List<StationInfoBean>>()
            .await()
    }

    /**
     * 站点绑定MAC地址
     */
    suspend fun bindMac(id: String, mac: String): String {
        return RxHttp.putJson("/lh/lhSiteMgt/edit")
            .setAssemblyEnabled(false)
            .add("id", id)
            .add("mac", mac)
            .toStr()
            .await()
    }

    /**
     * 根据mac地址获取站点详情
     */
    suspend fun getStationWithMac(mac: String): StationDetailBean {
        return RxHttp.get("/lh/lhSiteMgt/edit")
            .setAssemblyEnabled(false)
            .add("mac", mac)
            .toResponse<StationDetailBean>()
            .await()
    }

    /**
     * 分页查询设备列表
     */
    suspend fun getDeviceList(pageNo: Int, site: String): DevicePageBean {
        return RxHttp.get("/lh/lhEqpMgt/list")
            .setAssemblyEnabled(false)
            .add("pageNo", pageNo)
            .add("pageSize", 20)
            .add("site", site)
            .toResponse<DevicePageBean>()
            .await()
    }

}