package cn.com.dictionary.dao.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/24 14:01
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -7260208264572154128L;

    private String id;
    private String name;
    private String key;
    private String lock;
    private String status;
    private String remark;
    private String createTime;
}
