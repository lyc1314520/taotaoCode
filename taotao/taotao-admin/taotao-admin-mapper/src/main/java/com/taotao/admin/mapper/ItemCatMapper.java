package com.taotao.admin.mapper;

import com.taotao.admin.pojo.ItemCat;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.mapper
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:172017/8/22
 * @Modified By:
 */
public interface ItemCatMapper extends Mapper<ItemCat> {

    @Select("select id,name as text,is_parent as state from tb_item_cat where parent_id=#{parentId} order by id asc")
    List<Map<String,Object>> getItemCatByParentId(Long parentId);
}
