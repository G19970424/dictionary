package cn.com.dictionary.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author gejj
 * @data 2023/5/19 16:56
 */
@Data
@TableName("T_TR_USER_LOGIN_LOG")
public class UserLoginLog {
    @TableId(value = "f_id",type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private Date date;
    private boolean status;
}
