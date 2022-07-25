package com.dream.greenmerge.bean

data class UserInfoBean(
    val admin: Boolean,
    val avatar: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val dept: Dept,
    val deptId: Int,
    val email: String,
    val loginDate: String,
    val loginIp: String,
    val nickName: String,
    val params: ParamsX,
    val phonenumber: String,
    val postIds: String,
    val projectId: String,
    val remark: String,
    val roleId: String,
    val roleIds: String,
    val roles: List<Role>,
    val salt: String,
    val searchValue: String,
    val sex: String,
    val status: String,
    val updateBy: String,
    val updateTime: String,
    val userId: Int,
    val userName: String
)

data class Dept(
    val ancestors: String,
    val children: List<String>,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val deptId: Int,
    val deptName: String,
    val email: String,
    val leader: String,
    val orderNum: String,
    val params: Params,
    val parentId: Int,
    val parentName: String,
    val phone: String,
    val remark: String,
    val searchValue: String,
    val status: String,
    val updateBy: String,
    val updateTime: String
)

class ParamsX

data class Role(
    val admin: Boolean,
    val createBy: String,
    val createTime: String,
    val dataScope: String,
    val delFlag: String,
    val deptCheckStrictly: Boolean,
    val deptIds: String,
    val flag: Boolean,
    val menuCheckStrictly: Boolean,
    val menuIds: String,
    val params: ParamsXX,
    val remark: String,
    val roleId: Int,
    val roleKey: String,
    val roleName: String,
    val roleSort: String,
    val searchValue: String,
    val status: String,
    val updateBy: String,
    val updateTime: String
)

class Params

class ParamsXX