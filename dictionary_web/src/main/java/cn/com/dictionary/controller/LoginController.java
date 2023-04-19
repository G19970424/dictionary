package cn.com.dictionary.controller;

import cn.com.dictionary.common.response.ApiResultResponse;
import cn.com.sge.dictionary.mapper.pojo.UserPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public ApiResultResponse doLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password){
        logger.info("%d {yyyy-MM-dd HH：mm：ss} 用户登录：${}",username);
        ApiResultResponse result = new ApiResultResponse<UserPojo>();

        UserPojo user = new UserPojo();
        user.setUsername("1234");
        user.setPassword("12345");
        user.setStatus(1);
        result.setData(user);
        return result;
    }
}
