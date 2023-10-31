package com.dream.greenmerge.main

import android.os.Bundle
import android.os.Handler
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.blankj.utilcode.util.LogUtils
import com.dream.greenmerge.adapter.ImageAdapter
import com.dream.greenmerge.bean.IndicatorBean
import com.dream.greenmerge.common.MmkvConstant.KEY_USER_PROJECT_ID
import com.dream.greenmerge.databinding.ActivityMainBinding
import com.dream.greenmerge.dialog.BindMacDialog
import com.dream.greenmerge.main.adapter.IndicatorAdapter
import com.dream.greenmerge.net.Configs
import com.dream.greenmerge.utils.DeviceIdUtil
import com.dream.greenmerge.utils.DialogHelp
import com.tcl.base.common.adapter.MyFragmentPagerAdapter
import com.tcl.base.common.ui.BaseActivity
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktToastShow
import com.tcl.base.kt.nullToEmpty
import com.tcl.base.utils.MmkvUtil
import com.tcl.base.weiget.recylerview.WaterFallItemDecoration
import java.text.SimpleDateFormat
import java.util.*


/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    lateinit var mHandler: Handler
    val mIndicatorAdapter = IndicatorAdapter()
    private var siteId = ""
    private var pageNo = 1
    var deviceId = ""
    val bannerRunTime = 1000L * 8
    val refreshTime = 1000L * 30
    private val mRunnable = object : Runnable {
        override fun run() {
            viewModel.deviceData.value?.pages?.let {
                val next = (mBinding.deviceVp.currentItem + 1) % it
                mBinding.deviceVp.currentItem = next
                mIndicatorAdapter.data.forEachIndexed { index, indicatorBean ->
                    indicatorBean.isCur = index == next
                }
                mIndicatorAdapter.notifyDataSetChanged()
                mHandler.removeCallbacks(this)
                mHandler.postDelayed(this, bannerRunTime)
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mHandler = Handler(mainLooper)
        deviceId = DeviceIdUtil.getDeviceId(this)
        mBinding.indicatorRv.apply {
            adapter = mIndicatorAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            addItemDecoration(WaterFallItemDecoration(18, 0))
        }
        mBinding.preIv.ktClick {
            pageNo--
            if (pageNo < 1) {
                pageNo = mIndicatorAdapter.data.size
            }
            mBinding.deviceVp.currentItem = pageNo - 1
            mIndicatorAdapter.data.forEachIndexed { index, indicatorBean ->
                indicatorBean.isCur = index + 1 == pageNo
            }
            mIndicatorAdapter.notifyDataSetChanged()
        }
        mBinding.nextIv.ktClick {
            pageNo++
            if (pageNo > mIndicatorAdapter.data.size) {
                pageNo = 1
            }
            mBinding.deviceVp.currentItem = pageNo - 1
            mIndicatorAdapter.data.forEachIndexed { index, indicatorBean ->
                indicatorBean.isCur = index + 1 == pageNo
            }
            mIndicatorAdapter.notifyDataSetChanged()
        }

        initTime()
        mHandler.removeCallbacksAndMessages("123")
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())
                val split = date.split(" ")
                mBinding.ymd.text = split[0]
                mBinding.timeTv.text = split[1]

                if (viewModel.isBindMac.value == true) {
                    viewModel.getStationWithMac(deviceId)
                }
                mHandler.postDelayed(this, refreshTime)
            }
        }, 1000 * 30)//每20s刷新页面
    }


    private fun initTime() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val week = calendar.get(Calendar.DAY_OF_WEEK)
        var tempWeek = "星期一"
        when (week) {
            0 -> {
                tempWeek = "星期六"
            }

            1 -> {
                tempWeek = "星期日"
            }

            2 -> {
                tempWeek = "星期一"
            }

            3 -> {
                tempWeek = "星期二"
            }

            4 -> {
                tempWeek = "星期三"
            }

            5 -> {
                tempWeek = "星期四"
            }

            6 -> {
                tempWeek = "星期五"
            }
        }
        mBinding.weekTv.text = tempWeek
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.timeInMillis)
        val split = date.split(" ")
        mBinding.ymd.text = split[0]
        mBinding.timeTv.text = split[1]
    }

    override fun initData() {
        viewModel.isBindMac(deviceId)
        mBinding.mac.ktClick {
            MmkvUtil.decodeString(KEY_USER_PROJECT_ID)?.let {
                viewModel.getStationUnbindMac(it)
            }
        }
    }

    override fun initDataOnResume() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.unBindData.observe(this) {
            if (it.isNullOrEmpty()) {
                "暂无可用站点".ktToastShow()
            } else {
                BindMacDialog(this, it) {
                    LogUtils.dTag("macTag", deviceId)
                    viewModel.bindMac(
                        it,
                        deviceId
                    )
                }.show()
            }
        }
        viewModel.stationDetailResult.observe(this) {
            mBinding.contentLay.isVisible = true
            it.site?.let { site ->
                mBinding.temperature.text = site.temperature.nullToEmpty() + "℃"
                mBinding.title.text = site.projectName + "-" + site.name.nullToEmpty()
                val url = Configs.getAppBaseUrl() + site.projectLogo
                LogUtils.dTag("ssdd", url)
                mBinding.contactTitle.text = "物业联系人：" + site.contact.nullToEmpty()
                mBinding.callTel.text = "物业联系电话：" + site.contactNumber.nullToEmpty()
                val split = site.deliveryTime?.split(";")
                mBinding.oneDate.isVisible = !split?.getOrNull(0).isNullOrEmpty()
                mBinding.twoDate.isVisible = !split?.getOrNull(1).isNullOrEmpty()
                mBinding.oneDate.text = split?.getOrNull(0).nullToEmpty()
                mBinding.twoDate.text = split?.getOrNull(1).nullToEmpty()
                mBinding.concentrationTv.text = site.airQuality.nullToEmpty()
                mBinding.deodorantTime.text = site.disinfectTime.nullToEmpty()
                viewModel.getDeviceList(1, site.id)
                siteId = site.id
            }
            it.project.let {
                val url = Configs.getAppBaseUrl() + it.logoPath
                LogUtils.dTag("logoUrl", url)
                mBinding.logoImg.load(url)
            }
            LogUtils.dTag("tz", "ad.size = " + it.ad.size)
            it.ad.forEach {
                it.imagePath = Configs.getAppBaseUrl() + it.imagePath
                LogUtils.dTag("uuu", "img = " + it.imagePath)
            }
            mBinding.bigImg.setAdapter(ImageAdapter(it.ad))
        }
        viewModel.isBindMac.observe(this) {
            if (it) {
                viewModel.getStationWithMac(deviceId)
            } else {
                DialogHelp.showTwoBottomDialog(
                    contentStr = "MAC地址还未绑定站点",
                    rightTextStr = "绑定站点",
                    rightOnClickBlock = {
                        MmkvUtil.decodeString(KEY_USER_PROJECT_ID)?.let {
                            viewModel.getStationUnbindMac(it)
                        }
                    }
                )
            }
        }
        viewModel.deviceData.observe(this) {
            mBinding.indicatorLay.isVisible = it.pages > 1
            if (it.pages > 1) {
                val indicatorList = arrayListOf<IndicatorBean>()
                repeat(it.pages) {
                    indicatorList.add(IndicatorBean(false))
                }
                mIndicatorAdapter.setList(indicatorList)
            }
            val fragments = arrayListOf<Fragment>()
            repeat(it.pages) {
                fragments.add(DeviceListFragment(siteId, it + 1))
            }
            mBinding.deviceVp.adapter = MyFragmentPagerAdapter(this, fragments)
            if (it.pages > 1) {
                mHandler.removeCallbacksAndMessages("456")
                mHandler.postDelayed(mRunnable, bannerRunTime)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages("1")
    }
}