package com.taotao.admin.mapper;

import com.taotao.admin.pojo.ContentCategory;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.mapper
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:012017/8/26
 * @Modified By:
 */
public interface ContentCategoryMapper extends Mapper<ContentCategory> {
    /**
     * 根据父节点查询所有的子节点
     * @param parentId 父节点id
     * @return List
     */
    @Select("select id as id, name as text, is_parent as state, parent_id as parentId from "
            + " tb_content_category where parent_id = #{parentId} order by id asc")
    List<Map<String,Object>> selectContentCategory(Long parentId);

}
