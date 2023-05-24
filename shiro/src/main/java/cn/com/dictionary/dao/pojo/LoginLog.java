package cn.com.dictionary.dao.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gejj
 * @data 2023/5/24 13:47
 */
@Data
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -1491539015490238320L;

    private String id;
    private String userId;
    private String loginTime;
    private boolean status;

}
