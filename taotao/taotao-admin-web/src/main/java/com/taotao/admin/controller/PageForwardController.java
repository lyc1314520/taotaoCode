package com.taotao.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 15:432017/8/24
 * @Modified By:
 */
@Controller
public class PageForwardController {

    /**
     * @Description:请求到1指定的页面
     * @Author: LYC
     */
    @GetMapping("/page/{viewName}")
    public String forward(@PathVariable("viewName")String viewName){
        return viewName;
    }
}
