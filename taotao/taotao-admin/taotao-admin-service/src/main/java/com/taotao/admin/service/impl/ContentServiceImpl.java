package com.taotao.admin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.admin.pojo.Content;
import com.taotao.admin.service.ContentService;
import com.taotao.admin.service.redis.RedisService;
import com.taotao.common.vo.DataGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private ObjectMapper objectMapper = new ObjectMapper();
    /** 注入RedisService对象 */
    @Autowired
    private RedisService redisService;
    /** 定义存放在Redis中大广告的key */
    private final static String REDIS_INDEX_BIGAD = "redis_index_bigad";

    /**
     * 根据分类分页查询内容
     *
     * @param categoryId 分类的id
     * @param page       当前页码
     * @param rows       每页显示的行数
     * @return DataGridResult
     */
    public DataGridResult selectContentByPage(final Long categoryId, Integer page,
                                              Integer rows) {

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

    /** 查询大广告数据 */
    public String findBigAdData(){
        /**
         * [{
         "alt": "",
         "height": 240,
         "heightB": 240,
         "href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
         "src": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
         "srcB": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
         "width": 670,
         "widthB": 550
         },{},...]
         */
        try{

            try{
                /** ############## 从Redis中查询大广告数据，如果有就直接返回 ################# */
                String bigAdData = redisService.get(REDIS_INDEX_BIGAD);
                if (StringUtils.isNoneBlank(bigAdData)){
                    System.out.println("redis中查询大广告数据: " + bigAdData);
                    return bigAdData;
                }
            }catch(Exception ex){};

            /** 定义List集合封装数据 */
            List<Map<String, Object>> data = new ArrayList<>();

            /** 查询大广告数据 (6条数据)  10 */
            @SuppressWarnings("unchecked")
            List<Content> contentLists = (List<Content>)selectContentByPage(4L, 1, 6).getRows();

            /** 迭代contentLists集合 */
            for (Content content : contentLists){
                /**
                 *  "alt": "",
                 "height": 240,
                 "heightB": 240,
                 "href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
                 "src": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
                 "srcB": "http://image.taotao.com/images/2015/03/03/2015030304360302109345.jpg",
                 "width": 670,
                 "widthB": 550
                 */
                Map<String, Object> json = new HashMap<>();
                json.put("alt", content.getTitle());
                json.put("height", 240);
                json.put("heightB", 240);
                json.put("href", content.getUrl());
                json.put("src", content.getPic());
                json.put("srcB", content.getPic2());
                json.put("width", 670);
                json.put("widthB", 550);

                data.add(json);
            }

            String bigAdData = objectMapper.writeValueAsString(data);

            try{
                /** ############## 把大广告数据存入Redis数据库中 ################# */
                redisService.setex(REDIS_INDEX_BIGAD, bigAdData, 60 * 60 * 24);
            }catch(Exception ex){};

            return bigAdData;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
