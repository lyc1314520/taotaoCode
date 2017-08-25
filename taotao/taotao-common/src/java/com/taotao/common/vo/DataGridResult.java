package com.taotao.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.taotao.common.vo
 * @Description:封装DataGrid组件响应数据类
 * @Author: LYC
 * @Date: Created in 19:402017/8/25
 * @Modified By:
 */
public class DataGridResult implements Serializable {

    private static final long serialVersionUID = -8173730615611899988L;
    /** 定义总记录数 */
    private Long    total;
    /** 定义数据对应rows */
    private List<?> rows;
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
