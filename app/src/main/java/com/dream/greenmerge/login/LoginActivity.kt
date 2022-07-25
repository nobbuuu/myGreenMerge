package com.dream.greenmerge.login

import android.os.Bundle
import com.dream.greenmerge.databinding.ActivityLaunchBinding
import com.dream.greenmerge.main.MainActivity
import com.tcl.base.common.ui.BaseActivity
import com.tcl.base.kt.ktClick
import com.tcl.base.kt.ktStartActivity
import com.tcl.base.kt.ktToastShow

class LoginActivity : BaseActivity<LoginViewModel, ActivityLaunchBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

        mBinding.login.ktClick {
            val userName = mBinding.username.text.toString()
            val password = mBinding.password.text.toString()
            if (userName.isEmpty()) {
                "请输入用户名".ktToastShow()
                return@ktClick
            }
            if (password.isEmpty()) {
                "请输入密码".ktToastShow()
                return@ktClick
            }
            viewModel.login(userName,password)
        }

    }

    override fun initData() {
    }


    override fun initDataOnResume() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.loginResult.observe(this) {
            ktStartActivity(MainActivity::class)
        }
    }
}