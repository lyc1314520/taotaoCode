package com.taotao.admin.service.impl;

import com.taotao.admin.mapper.ItemDescMapper;
import com.taotao.admin.mapper.ItemMapper;
import com.taotao.admin.pojo.Item;
import com.taotao.admin.pojo.ItemDesc;
import com.taotao.admin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:132017/8/24
 * @Modified By:
 */
@Service
@Transactional(readOnly = false)
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * 添加商品
     * @param item 商品信息
     * @param desc 描述信息
     */
    public void saveItem(Item item, String desc){
        /** 添加商品 */
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insertSelective(item);

        /** 添加商品描述 */
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescMapper.insertSelective(itemDesc);
    }
    /**
     * 修改商品
     * @param item 商品信息
     * @param desc 描述信息
     */
    @Override
    public void updateItem(Item item, String desc) {
        /** 修改商品 */
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);

        /** 修改商品描述 */
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }
}
