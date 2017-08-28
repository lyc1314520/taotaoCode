package com.taotao.admin.service;

import com.taotao.admin.pojo.Content;
import com.taotao.common.vo.DataGridResult;

/**
 * @Package: com.taotao.admin.service
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:022017/8/26
 * @Modified By:
 */
public interface ContentService extends BaseService<Content> {

    DataGridResult selectContentByPage(Long categoryId, Integer page, Integer rows);

    String findBigAdData();

}
