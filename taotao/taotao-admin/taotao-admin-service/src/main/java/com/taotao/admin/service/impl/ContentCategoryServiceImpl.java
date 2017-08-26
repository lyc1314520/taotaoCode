package com.taotao.admin.service.impl;

import com.taotao.admin.mapper.ContentCategoryMapper;
import com.taotao.admin.pojo.ContentCategory;
import com.taotao.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:052017/8/26
 * @Modified By:
 */
@Service
@Transactional(readOnly = false,rollbackFor = RuntimeException.class)
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<Map<String, Object>> selectContentCategory(Long parentId) {
        List<Map<String,Object>> data = contentCategoryMapper.selectContentCategory(parentId);
        for (Map<String,Object>map:data) {
            map.put("state",(boolean)map.get("state") ? "closed" : "open");
        }
        return data;
    }

    /**
     * 添加内容分类
     * @param contentCategory 内容分类
     * @return 主键id值
     */
    public Long saveContentCategroy(ContentCategory contentCategory){

        /** 添加内容分类 */
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(contentCategory.getCreated());
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategoryMapper.insertSelective(contentCategory);

        /** 修改父节点 */
        ContentCategory parent = new ContentCategory();
        parent.setId(contentCategory.getParentId());
        parent.setUpdated(new Date());
        parent.setIsParent(true);
        contentCategoryMapper.updateByPrimaryKeySelective(parent);

        return contentCategory.getId();
    }

    /**
     * 删除内容分类
     * @param contentCategory 内容分类
     */
    public void deleteContentCategroy(ContentCategory contentCategory){
        /** 定义List集合封装需要删除的树节点id */
        List<Long> ids = new ArrayList<>();
        /** 添加自己的节点 */
        ids.add(contentCategory.getId());
        /** 递归查询所有的子节点 */
        findLeafNode(ids, contentCategory.getId());
        /** 批量删除树节点 */
        this.deleteAll("id", ids.toArray(new Long[ids.size()]));

        /** 判断父节点有没有子节点
         * select count(*) from tb_content_category where parent_id = parentId
         * */
        ContentCategory cc = new ContentCategory();
        cc.setParentId(contentCategory.getParentId());
        Integer count = this.countByWhere(cc);
        if (count == 0){
            /** 代表没有子节点，需要修改is_parent为false */
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            parent.setUpdated(new Date());
            this.updateSelective(parent);
        }

    }

    /**
     * 递归查询所有的子节点
     * @param ids List集合
     * @param id 父节点id
     */
    private void findLeafNode(List<Long> ids, Long id) {
        /**
         * 根据父节点id查询所有的子节点
         * select * from tb_content_category where parent_id = id
         * */
        ContentCategory cc = new ContentCategory();
        cc.setParentId(id);
        List<ContentCategory> nodeLists = this.findAllByWhere(cc);
        if (nodeLists != null && nodeLists.size() > 0){
            for (ContentCategory node : nodeLists){
                /** 添加子节点的id */
                ids.add(node.getId());
                findLeafNode(ids, node.getId());
            }
        }
    }

}
