package com.taotao.admin.controller;

import com.taotao.admin.pojo.Item;
import com.taotao.admin.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import com.taotao.common.vo.*;
/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:202017/8/24
 * @Modified By:
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /** 添加商品 */
    @PostMapping("/save")
    public void saveItem(Item item, @RequestParam("desc")String desc){
        /** 添加商品 */
        itemService.saveItem(item, desc);
    }

    /** 修改商品 */
    @PostMapping("/update")
    public void updateItem(Item item, @RequestParam("desc")String desc){
        /** 修改商品 */
        itemService.updateItem(item, desc);
    }

    /** 分页查询商品 */
    @GetMapping
    public DataGridResult selectItemByPage(Item item,
                                           @RequestParam("page")Integer page,
                                           @RequestParam("rows")Integer rows) throws Exception{
        if (item != null && StringUtils.isNoneBlank(item.getTitle())){
            item.setTitle(URLDecoder.decode(item.getTitle(), "utf-8"));
        }
        return itemService.selectItemByPage(item, page, rows);
    }

}
