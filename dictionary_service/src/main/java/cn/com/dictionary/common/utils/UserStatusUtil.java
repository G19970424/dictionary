package cn.com.dictionary.common.utils;

import cn.com.dictionary.utils.enumdata.StatusEnum;
import cn.com.dictionary.utils.enumdata.UserStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gejj
 * @package cn.com.dictionary.common.utils
 * @data 2023/4/21 11:28
 */
public class UserStatusUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserStatusUtil.class);

    /**
     * 校验用户是否生效状态
     * @return
     */
    public static boolean checkUserStatus(String status){
        if(UserStatusEnum.BLACKLISTED.getValue().equals(status) || UserStatusEnum.CANCEL.getValue().equals(status)) return false;
        return true;
    }
}
