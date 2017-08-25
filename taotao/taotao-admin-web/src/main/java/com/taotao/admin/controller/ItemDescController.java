package com.taotao.admin.controller;

import com.taotao.admin.pojo.ItemDesc;
import com.taotao.admin.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 20:052017/8/25
 * @Modified By:
 */
@RestController
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;

    @GetMapping("/itemdesc/{itemId}")
    public ItemDesc selectItem(@PathVariable("itemId")Long itemId){
        return itemDescService.findOne(itemId);
    }
}
