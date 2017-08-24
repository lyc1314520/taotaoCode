package com.taotao.admin.service;

import com.taotao.admin.pojo.ItemCat;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.service
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:182017/8/22
 * @Modified By:
 */
public interface ItemCatService extends BaseService<ItemCat> {
    /**
     * 根据父级id查询所有的物品类目
     * @param parentId 父级id
     * @return 集合
     */
    List<Map<String,Object>> findItemCatByParentId(Long parentId);

}
