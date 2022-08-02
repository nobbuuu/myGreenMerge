package com.dream.greenmerge.task

import android.app.Application
import android.webkit.WebView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.RomUtils
import com.dream.greenmerge.BuildConfig
import com.dream.greenmerge.common.MmkvConstant
import com.kingswim.mock.HttpMockInterceptor
import com.tcl.base.rxnetword.RxHttpManager
import com.tcl.base.utils.MmkvUtil
import com.tcl.launcher.task.MainTask
import com.dream.greenmerge.other.security.EncryptTShopInterceptor
import okhttp3.Interceptor
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.RxHttp
import java.io.File

/**
 * @author : Yzq
 * time : 2020/11/10 14:01
 */
const val storeUuid = "thome"

class InitHttpClient : MainTask() {

    private val userAgent: String by lazy { WebView(mContext).settings.userAgentString }
    override fun run() {
        initRxHttp()
    }

    private fun initRxHttp() {
        when (mContext) {
            is Application -> {

                val interceptors =
                    mutableListOf<Interceptor>(
                        EncryptTShopInterceptor()
                    ).apply {
                        if (BuildConfig.DEBUG) {
                            //添加模拟数据拦截器
                            add(HttpMockInterceptor())
                        }
                    }
                RxHttp.init(RxHttpManager.init(mContext as Application, interceptors))
                val file = File(mContext.cacheDir, "netCache")
                RxHttpPlugins.setCache(file, 10 * 1024 * 1024, 60 * 1000)
                RxHttp.setDebug(BuildConfig.DEBUG, true)
                RxHttp.setOnParamAssembly { param ->
                    //根据不同请求添加不同参数，子线程执行，每次发送请求前都会被回调
                    //如果希望部分请求不回调这里，发请求前调用Param.setAssemblyEnabled(false)即可
                    param.addHeader("Authorization", MmkvUtil.decodeString(MmkvConstant.KEY_ACCESS_TOKEN))
                }
            }
            else -> {
                throw Exception("Context must be Application")
            }
        }
    }
}