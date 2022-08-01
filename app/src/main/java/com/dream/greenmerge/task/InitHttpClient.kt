package com.dream.greenmerge.task

import android.app.Application
import android.webkit.WebView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.RomUtils
import com.dream.greenmerge.BuildConfig
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
            }
            else -> {
                throw Exception("Context must be Application")
            }
        }
    }
}