package cn.com.dictionary.dao.pojo;

/**
 * @author gejj
 * @package cn.com.dictionary.dao.pojo
 * @data 2023/4/25 16:15
 */
public class User {
    private String id;
    private String username;
    private String password;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
