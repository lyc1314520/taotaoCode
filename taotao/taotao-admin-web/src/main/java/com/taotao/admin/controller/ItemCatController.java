package com.taotao.admin.controller;

import com.taotao.admin.pojo.ItemCat;
import com.taotao.admin.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:262017/8/22
 * @Modified By:
 */
@RestController
@RequestMapping("/itemcat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/{page}")
    public List<ItemCat> findItemCatAll(@PathVariable("page")Integer page,
                                        @RequestParam(name = "rows",defaultValue = "15")Integer rows){
        try {
            return itemCatService.findByPage(page,rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:根据父类id查询物品类目
     * @Author: LYC
     */
    @GetMapping
    public List<Map<String,Object>> selectItemCatByParentId(@RequestParam(value="id",defaultValue = "0")Long parentId){
        try {
            return itemCatService.findItemCatByParentId(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
