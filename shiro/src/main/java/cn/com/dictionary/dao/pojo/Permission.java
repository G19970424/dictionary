package cn.com.dictionary.dao.pojo;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/15 16:43
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 931075634127877221L;

    private String id;
    private String parentId;
    private String name;
    private String type;
    private String url;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
