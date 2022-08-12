package com.dream.greenmerge.main

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.dream.greenmerge.adapter.ImageAdapter
import com.dream.greenmerge.bean.IndicatorBean
import com.dream.greenmerge.common.MmkvConstant.KEY_USER_PROJECT_ID
import com.dream.greenmerge.databinding.ActivityMainBinding
import com.dream.greenmerge.dialog.BindMacDialog
import com.dream.greenmerge.main.adapter.IndicatorAdapter
import com.dream.greenmerge.net.Configs
import com.dream.greenmerge.utils.DialogHelp
import com.tcl.base.common.adapter.MyFragmentPagerAdapter
import com.tcl.base.common.ui.BaseActivity
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktToastShow
import com.tcl.base.kt.loadGif
import com.tcl.base.kt.nullToEmpty
import com.tcl.base.utils.MmkvUtil
import com.tcl.base.weiget.recylerview.WaterFallItemDecoration


/**
 *@author tiaozi
 *@date   2021/12/8
 *description
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    val mIndicatorAdapter = IndicatorAdapter()
    private var isInitDevice = false
    private var siteId = ""
    private var pageNo = 1
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.bigImg.addBannerLifecycleObserver(this)
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
    }

    override fun initData() {
        viewModel.isBindMac(getMacAddress().nullToEmpty())

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
                    val mac = getMacAddress()
                    LogUtils.dTag("macTag", mac)
                    viewModel.bindMac(
                        it,
                        mac.nullToEmpty()
                    )
                }.show()
            }
        }
        viewModel.stationDetailResult.observe(this) {
            mBinding.contentLay.isVisible = true
            it.site?.let { site ->
                mBinding.temperature.text = site.temperature.nullToEmpty() + "℃"
                mBinding.title.text = site.name.nullToEmpty()
                val url = Configs.getAppBaseUrl() + site.projectLogo
                LogUtils.dTag("ssdd", url)
                mBinding.contactTitle.text = "物业联系人：" + site.contact.nullToEmpty()
                mBinding.callTel.text = "物业联系电话：" + site.contactNumber.nullToEmpty()
                val split = site.deliveryTime?.split(",")
                mBinding.oneDate.isVisible = !split?.getOrNull(0).isNullOrEmpty()
                mBinding.twoDate.isVisible = !split?.getOrNull(1).isNullOrEmpty()
                mBinding.oneDate.text = split?.getOrNull(0).nullToEmpty()
                mBinding.twoDate.text = split?.getOrNull(1).nullToEmpty()
                mBinding.concentrationTv.text = site.airQuality.nullToEmpty()
                mBinding.deodorantTime.text = site.disinfectTime.nullToEmpty()
                viewModel.getDeviceList(1, site.id)
                siteId = site.id
            }
            it.ad.forEach { it.imagePath = Configs.getAppBaseUrl() + it.imagePath
                LogUtils.dTag("uuu","img = " + it.imagePath)
            }
            mBinding.bigImg.setAdapter(ImageAdapter(it.ad),true)
        }
        viewModel.isBindMac.observe(this) {
            if (it) {
                viewModel.getStationWithMac(getMacAddress().nullToEmpty())
            }else{
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
            if (!isInitDevice) {
                mBinding.indicatorLay.isVisible = it.pages > 1
                if (it.pages > 1) {
                    val indicatorList = arrayListOf<IndicatorBean>()
                    repeat(it.pages) {
                        indicatorList.add(IndicatorBean(false))
                    }
                    mBinding.indicatorRv.apply {
                        adapter = mIndicatorAdapter
                        layoutManager =
                            LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                        addItemDecoration(WaterFallItemDecoration(18, 0))
                    }
                    mIndicatorAdapter.setList(indicatorList)
                }
                val fragments = arrayListOf<Fragment>()
                repeat(it.pages) {
                    fragments.add(DeviceListFragment(siteId, it + 1))
                }
                mBinding.deviceVp.adapter = MyFragmentPagerAdapter(this, fragments)
                isInitDevice = true
            }
        }
    }

    fun getMacAddress(): String? {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        return wifiInfo?.macAddress
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding.bigImg.destroy()
    }
}