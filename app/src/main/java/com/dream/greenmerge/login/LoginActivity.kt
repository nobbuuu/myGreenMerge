package com.dream.greenmerge.login

import android.os.Bundle
import com.dream.greenmerge.databinding.ActivityLaunchBinding
import com.dream.greenmerge.main.MainActivity
import com.tcl.base.common.ui.BaseActivity
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktStartActivity

class LoginActivity : BaseActivity<LoginViewModel, ActivityLaunchBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mBinding.login.ktClick {
            ktStartActivity(MainActivity::class)
        }
    }

    override fun initData() {
    }


    override fun initDataOnResume() {

    }
}