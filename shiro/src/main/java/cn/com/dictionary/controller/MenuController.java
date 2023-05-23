package cn.com.dictionary.controller;

import cn.com.dictionary.common.result.ApiCode;
import cn.com.dictionary.common.result.ApiResult;
import cn.com.dictionary.common.utils.ResultUtil;
import cn.com.dictionary.dao.pojo.Permission;
import cn.com.dictionary.dao.pojo.User;
import cn.com.dictionary.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gejj
 * @data 2023/5/19 14:24
 */
@Api(value = "Menu|动态获取登录用户菜单")
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取登录用户菜单接口",notes = "系统获取用户id")
    @GetMapping(value = "/getMenuById",consumes = "application/json")
    public ApiResult showMenu(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Permission> permissions = menuService.queryByUserId(user.getId());
        return ResultUtil.SUCCESS(permissions, ApiCode.SUCCESS.getCode());
    }
}
