package com.taotao.admin.service;

import com.taotao.admin.pojo.Item;

/**
 * @Package: com.taotao.admin.service
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:052017/8/24
 * @Modified By:
 */
public interface ItemService extends BaseService<Item> {
    /**
     * @Description:保存商品与商品描述
     * @Author: LYC
     */
    void saveItem(Item item,String desc);

    void updateItem(Item item, String desc);

}
