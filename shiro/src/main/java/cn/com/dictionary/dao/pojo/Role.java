package cn.com.dictionary.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/15 16:41
 */
@Data
@TableName("T_TR_ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = -6584491782489295720L;
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String locked;
    private String remark;
    private String status;


    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
