package com.taotao.admin.controller;

import com.taotao.admin.pojo.Content;
import com.taotao.admin.pojo.ContentCategory;
import com.taotao.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:152017/8/26
 * @Modified By:
 */
@RestController
@RequestMapping("/contentcategory")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父类节点查询所有子类节点
     */
    @GetMapping
    public List<Map<String,Object>> selectContentCategory(@RequestParam(name = "id",defaultValue = "0")Long parentId){
        return contentCategoryService.selectContentCategory(parentId);
    }

    /** 添加内容分类 */
    @PostMapping("/save")
    public Long saveContentCategroy(ContentCategory contentCategory){
        return contentCategoryService.saveContentCategroy(contentCategory);
    }

    /** 修改内容分类 */
    @PostMapping("/update")
    public void updateContentCategroy(ContentCategory contentCategory){
        contentCategory.setUpdated(new Date());
        contentCategoryService.updateSelective(contentCategory);
    }

    /** 删除内容分类 */
    @PostMapping("/delete")
    public void deleteContentCategroy(ContentCategory contentCategory){
        contentCategoryService.deleteContentCategroy(contentCategory);
    }
}
