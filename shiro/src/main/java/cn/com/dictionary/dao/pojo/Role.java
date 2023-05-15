package cn.com.dictionary.dao.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * @author gejj
 * @data 2023/5/15 16:41
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -6584491782489295720L;
    private String id;
    private String name;
    private String locked;
    private String remark;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
