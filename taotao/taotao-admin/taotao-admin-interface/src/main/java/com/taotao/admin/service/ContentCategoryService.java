package com.taotao.admin.service;

import com.taotao.admin.pojo.ContentCategory;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taotao.admin.service
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:032017/8/26
 * @Modified By:
 */
public interface ContentCategoryService extends BaseService<ContentCategory> {

    List<Map<String,Object>> selectContentCategory(Long parentId);

    Long saveContentCategroy(ContentCategory contentCategory);

    void deleteContentCategroy(ContentCategory contentCategory);

}
