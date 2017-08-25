package com.taotao.admin.mapper;

import com.taotao.admin.pojo.Item;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.mapper
 * @Description:商品数据访问接口
 * @Author: LYC
 * @Date: Created in 19:042017/8/24
 * @Modified By:
 */
public interface ItemMapper extends Mapper<Item> {

    /**
     * 查询商品
     * @param item 查询条件
     * @return List
     */
    List<Map<String, Object>> findItemByWhere(@Param("item")Item item);
}
