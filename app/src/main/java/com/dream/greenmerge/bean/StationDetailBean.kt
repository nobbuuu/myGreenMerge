package com.dream.greenmerge.bean

data class StationDetailBean(
    val ad: List<Ad>,
    val project: Project,
    val site: Site
)

data class Ad(
    val adLink: String,
    val address: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val endTime: String,
    val id: String,
    val imagePath: String,
    val info: String,
    val name: String,
    val projectId: String,
    val remark: String,
    val startTime: String,
    val status: Int,
    val updateBy: String,
    val updateTime: String
)

data class Project(
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val id: String,
    val logoPath: String,
    val name: String,
    val password: String,
    val remark: String,
    val status: Int,
    val updateBy: String,
    val updateTime: String,
    val userId: String,
    val username: String
)

data class Site(
    val airQuality: String,
    val cleanPeriod: String,
    val cleanTime: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val deodorantPeriod: String,
    val deodorantTime: String,
    val deviceIds: String,
    val disinfectDuration: String,
    val disinfectInterval: String,
    val id: String,
    val mac: String,
    val moisture: String,
    val name: String,
    val projId: String,
    val projectLogo: String,
    val projectName: String,
    val remark: String,
    val status: Int,
    val temperature: String,
    val type: Int,
    val updateBy: String,
    val updateTime: String
)