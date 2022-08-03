package com.dream.greenmerge.bean

import java.io.Serializable

data class SelectInfoBean(
    var isCheck: Boolean = false,
    var name: String
) : Serializable
