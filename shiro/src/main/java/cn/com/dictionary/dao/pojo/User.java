package cn.com.dictionary.dao.pojo;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/15 16:26
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3456513675416484868L;
    private String id;
    private String username;
    private String password;
    private String sex;
    private int status;
    private int loginNumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(int loginNumber) {
        this.loginNumber = loginNumber;
    }

}
