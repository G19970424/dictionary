package cn.com.dictionary.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/15 16:43
 */
@Data
@TableName("T_TR_MENU")
public class Permission implements Serializable {

    private static final long serialVersionUID = 931075634127877221L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private String name;
    private String type;
    private String url;
    private String remark;


    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
