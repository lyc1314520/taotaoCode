package com.taotao.portal.controller;

import com.taotao.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Package: com.taotao.portal.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 16:162017/8/26
 * @Modified By:
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    /**
     * 跳转的门户
     */
    @GetMapping("/index")
    public String index(ModelMap modelMap){
        /** 准备响应数据  */
        modelMap.addAttribute("bigAdData", contentService.findBigAdData());
        return "index";
    }
}
