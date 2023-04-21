package cn.com.dictionary.controller;

import cn.com.dictionary.common.ApiResult;
import cn.com.dictionary.service.IKeyStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author gejj
 * @package cn.com.dictionary.controller
 * @data 2023/4/20 17:47
 * Administrator Management System Secret Key
 */
@Controller
@RequestMapping("/KeyManager")
public class KeyStoreController {

    private static final Logger logger = LoggerFactory.getLogger(KeyStoreController.class);

    @Autowired
    private IKeyStoreService keyStoreService;

    @RequestMapping("/generateSecret")
    @ResponseBody
    public ApiResult generateSecret(@ModelAttribute("name") String name){
        ApiResult result = keyStoreService.initializeKey(name);
        return result;
    }
}
