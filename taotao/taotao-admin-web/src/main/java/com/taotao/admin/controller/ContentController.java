package com.taotao.admin.controller;

import com.taotao.admin.pojo.Content;
import com.taotao.admin.service.ContentService;
import com.taotao.common.vo.DataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:072017/8/26
 * @Modified By:
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /** 根据分类分页查询内容 */
    @GetMapping
    public DataGridResult selectContentByPage(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows){
        return contentService.selectContentByPage(categoryId,page,rows);
    }

    /** 添加内容 */
    @PostMapping("/save")
    public void saveContent(Content content){
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        contentService.saveSelective(content);
    }

    /** 修改内容 */
    @PostMapping("/update")
    public void updateContent(Content content){
        content.setUpdated(new Date());
        contentService.updateSelective(content);
    }

    /** 删除内容 */
    @PostMapping("/delete") // 1,2,3
    public void deleteContent(@RequestParam("ids")String ids){
        contentService.deleteAll("id", ids.split(","));
    }


}
