package cn.com.dictionary.mapper.pojo;

import java.util.Date;

/**
 * @author gejj
 * @package cn.com.dictionary.mapper.pojo
 * @data 2023/4/20 14:49
 */
public class UserLogPojo {
    private String logId;
    private String userId;
    private String IpAddress;
    private Boolean loginStatus;
    private Date loginTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
