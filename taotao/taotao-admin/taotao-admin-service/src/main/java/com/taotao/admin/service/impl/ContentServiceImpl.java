package com.taotao.admin.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.admin.pojo.Content;
import com.taotao.admin.service.ContentService;
import com.taotao.common.vo.DataGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 17:032017/8/26
 * @Modified By:
 */
@Service
@Transactional(readOnly = false,rollbackFor = RuntimeException.class)
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    /**
     * 根据分类分页查询内容
     * @param categoryId 分类的id
     * @param page 当前页码
     * @param rows 每页显示的行数
     * @return DataGridResult
     */
    public DataGridResult selectContentByPage(final Long categoryId, Integer page,
                                              Integer rows){

        /** 开启分页 */
        PageInfo<Content> pageInfo = PageHelper.startPage(page, rows)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        Content content = new Content();
                        content.setCategoryId(categoryId);
                        findAllByWhere(content);
                    }
                });
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setTotal(pageInfo.getTotal());
        dataGridResult.setRows(pageInfo.getList());
        return dataGridResult;
    }
}
