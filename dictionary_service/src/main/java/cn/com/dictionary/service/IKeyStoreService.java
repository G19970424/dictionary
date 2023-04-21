package cn.com.dictionary.service;

import cn.com.dictionary.common.ApiResult;

/**
 * @author gejj
 * @package cn.com.dictionary.service
 * @data 2023/4/20 17:52
 */
public interface IKeyStoreService {
    /**
     * 初始化秘钥库
     * @param name
     * @return
     */
    ApiResult initializeKey(String name);
}
