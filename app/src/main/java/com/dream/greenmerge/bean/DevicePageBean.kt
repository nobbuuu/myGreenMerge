package com.dream.greenmerge.bean

data class DevicePageBean(
    val countId: String,
    val current: Int,
    val maxLimit: String,
    val optimizeCountSql: Boolean,
    val orders: List<String>,
    val pages: Int,
    val records: List<Record>,
    val searchCount: Boolean,
    val size: Int,
    val total: Int
)

data class Record(
    val ashcanSituation: String,
    val cleanSituation: String,
    val clearTime: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val deodorantSituation: String,
    val deodorantTime: String,
    val emergency: String,
    val eqpNum: String,
    val errorCode: String,
    val fire: String,
    val id: String,
    val medicine: String,
    val name: String,
    val projectName: String,
    val remark: String,
    val site: String,
    val siteName: String,
    val status: Int,
    val updateBy: String,
    val updateTime: String
)