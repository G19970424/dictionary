package cn.com.dictionary.dao.pojo;

import java.io.Serializable;

/**
 * @author gejj
 * @package cn.com.dictionary.dao.pojo
 * @data 2023/4/26 16:57
 */
public class RoleMenu implements Serializable {

    private int id;
    private int roleId;
    private int menuId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
