package cn.com.dictionary.common.response;

/**
 * @author gejj
 * @package cn.com.dictionary.common.response
 * @data 2023/4/25 15:09
 */
public class Permission {
    private Integer id;
    private String permissionName;
//    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

//    public Role getRoleBean() {
//        return role;
//    }
//
//    public void setRoleBean(Role role) {
//        this.role = role;
//    }
}
