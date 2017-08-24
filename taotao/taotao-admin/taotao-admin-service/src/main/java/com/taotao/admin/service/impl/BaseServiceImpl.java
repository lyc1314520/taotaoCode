package com.taotao.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.taotao.admin.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 15:482017/8/24
 * @Modified By:
 */
@SuppressWarnings("unchecked")
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    /**定义当前访问的实体类**/
    private Class<T> entiyClass;

    public BaseServiceImpl(){
        /**获取类上面泛型的参数类型**/
        ParameterizedType parameterizedType =(ParameterizedType )this.getClass().getGenericSuperclass();
        /**获取所以的实际参数类型,取第一个**/
        this.entiyClass=(Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    /** 选择性添加 */
    @Override
    public void saveSelective(T entity) {
        mapper.insertSelective(entity);
    }
    /** 选择性修改 */
    @Override
    public void updateSelective(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }
    /** 根据主键删除 */
    @Override
    public void delete(Serializable id) {
        mapper.deleteByPrimaryKey(id);
    }
    /** 批量删除 */
    @Override
    public void deleteAll(String idField, Serializable[] ids) {
        //创建示范对象
        Example example = new Example(entiyClass);
        //创建条件对象
        Criteria criteria = example.createCriteria();
        //添加条件
        criteria.andIn(idField, Arrays.asList(ids));
        //根据条件删除
        mapper.deleteByExample(example);
    }
    /**根据主键id查询*/
    @Override
    public T findOne(Serializable id) {
        return mapper.selectByPrimaryKey(id);
    }
    /**查询所以记录*/
    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }
    /**等于号条件查询*/
    @Override
    public List<T> findAllByWhere(T entity) {
        return mapper.select(entity);
    }
    /**等于号条件统计查询*/
    @Override
    public int countByWhere(T entity) {
        return mapper.selectCount(entity);
    }
    /**分页查询*/
    @Override
    public List<T> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }
}
