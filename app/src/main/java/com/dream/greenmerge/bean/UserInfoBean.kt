data class UserInfoBean(
    val code: Int,
    val msg: String,
    val permissions: List<String>,
    val roles: List<String>,
    val user: User
)

data class User(
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
    val postIds: Any,
    val projectId: Any,
    val remark: String,
    val roleId: Any,
    val roleIds: Any,
    val roles: List<Role>,
    val salt: Any,
    val searchValue: Any,
    val sex: String,
    val status: String,
    val updateBy: Any,
    val updateTime: Any,
    val userId: Int,
    val userName: String
)

data class Dept(
    val ancestors: Any,
    val children: List<Any>,
    val createBy: Any,
    val createTime: Any,
    val delFlag: Any,
    val deptId: Int,
    val deptName: String,
    val email: Any,
    val leader: String,
    val orderNum: String,
    val params: Params,
    val parentId: Int,
    val parentName: Any,
    val phone: Any,
    val remark: Any,
    val searchValue: Any,
    val status: String,
    val updateBy: Any,
    val updateTime: Any
)

class ParamsX

data class Role(
    val admin: Boolean,
    val createBy: Any,
    val createTime: Any,
    val dataScope: String,
    val delFlag: Any,
    val deptCheckStrictly: Boolean,
    val deptIds: Any,
    val flag: Boolean,
    val menuCheckStrictly: Boolean,
    val menuIds: Any,
    val params: ParamsXX,
    val remark: Any,
    val roleId: Int,
    val roleKey: String,
    val roleName: String,
    val roleSort: String,
    val searchValue: Any,
    val status: String,
    val updateBy: Any,
    val updateTime: Any
)

class Params

class ParamsXX