package cn.com.dictionary.dao.pojo;

/**
 * @author gejj
 * @package cn.com.dictionary.dao.pojo
 * @data 2023/4/27 16:58
 */
public class Menu {
    private Integer id;
    private Integer pid;
    private String name;
    private Integer type;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
