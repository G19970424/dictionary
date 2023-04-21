package cn.com.dictionary.service.impl;

import cn.com.dictionary.common.ApiResult;
import cn.com.dictionary.service.IKeyStoreService;
import cn.com.dictionary.utils.AsymmetricCryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author gejj
 * @package cn.com.dictionary.service.impl
 * @data 2023/4/20 17:52
 */
@Service
public class KeyStoreServiceImpl implements IKeyStoreService {

    private static final Logger logger = LoggerFactory.getLogger(KeyStoreServiceImpl.class);

    @Override
    public ApiResult initializeKey(String name) {
        logger.info("--{} 秘钥开始生成--",name);
        ApiResult result = new ApiResult<>();
        Boolean flag = AsymmetricCryptoUtils.generateKeyPair(name);

        result.setSuccess(flag);
        result.setCode("200");
        if(flag){
            result.setMsg(name+"秘钥已完成！");
        }else{
            result.setMsg(name+"秘钥生成失败！");
        }
        logger.info("--{}秘钥生成完毕：{}",name,flag);
        return result;
    }
}
