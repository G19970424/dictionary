package cn.com.dictionary.dao.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/24 13:37
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5033265831302429356L;
    private String id;
    private String username;
    private String password;
    private String salt;
    private boolean lock;
    private String sex;
    private boolean status;
    private String createTime;
    private String lastLoginTime;
}
