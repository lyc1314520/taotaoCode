package com.taotao.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.admin.mapper.ItemCatMapper;
import com.taotao.admin.pojo.ItemCat;
import com.taotao.admin.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:222017/8/22
 * @Modified By:
 */
@Service
@Transactional(readOnly = false,rollbackFor = RuntimeException.class)
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 根据父级id查询所有的物品类目
     * @param parentId 父级id
     * @return 集合
     */
    @Override
    public List<Map<String, Object>> findItemCatByParentId(Long parentId) {
        List<Map<String, Object>> itemCat = itemCatMapper.getItemCatByParentId(parentId);
        System.out.println(itemCat);
        for (Map<String, Object> map:itemCat) {
            boolean state = (boolean)map.get("state");
            map.put("state",state ? "closed" : "open");
        }
        System.out.println(itemCat);
        return itemCat;
    }
}
