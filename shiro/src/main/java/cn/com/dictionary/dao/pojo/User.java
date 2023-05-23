package cn.com.dictionary.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gejj
 * @data 2023/5/15 16:26
 */
@TableName("T_TR_USER")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3456513675416484868L;

    @TableId
    private String id;
    private String username;
    private String password;
    private boolean sex;
    private String salt;
    private int status;
    private int loginNumber;
    private Date createDate;

    @Override
    public String toString() {
        return "username:"+ username;
    }

}
