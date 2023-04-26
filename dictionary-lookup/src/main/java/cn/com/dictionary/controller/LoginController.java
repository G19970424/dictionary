package cn.com.dictionary.controller;

import cn.com.dictionary.common.response.ApiResult;
import cn.com.dictionary.common.utils.EncryptionUtil;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gejj
 * @package cn.com.dictionary.controller
 * @data 2023/4/14 14:05
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginApiService;
    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public ApiResult doLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password){
        logger.info("%d {yyyy-MM-dd HH：mm：ss} 用户 {} 登录",username);
        //通过解密工具解密前端加密密码
        //通过加密工具加密用户密码
        password = EncryptionUtil.passwordEncrypt(password);
        User result = loginApiService.authenticate(username, password);
        return ApiResult.ok(result);
    }
}
