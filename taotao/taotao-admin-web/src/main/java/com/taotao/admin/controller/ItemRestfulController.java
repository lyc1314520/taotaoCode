package com.taotao.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.admin.pojo.Item;
import com.taotao.admin.service.ItemService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @Package: com.taotao.admin.controller
 * @Description:
 * @Author: LYC
 * @Date: Created in 15:332017/8/29
 * @Modified By:
 */
@Controller
@RequestMapping("/item/restful")
public class ItemRestfulController {
    @Autowired
    private ItemService itemService;
    /** 提供signKey 签名密钥： MD5加密的32长度字符串 */
    private final static String  SIGN_KEY     = "d869d887c6cef94cf449618de9e757c7";
    private  ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 根据id查询全部商品
     * @param id
     * @param sign
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id")Long id, @RequestParam("sign")String sign){
        try {
            /**判断请求参数*/
            if (id != null && id > 0){
            /**判断参数签名*/
            if (DigestUtils.md5Hex(SIGN_KEY + id).equals(sign)){
                Item item = itemService.findOne(id);
                if (item != null){
                    /**成功响应码状态200*/
                    return ResponseEntity.ok().body(item);
                }
                //没有查询到数据404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 服务器内容错误 500 */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /** 添加商品 */
    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody Map<String, String> map){
        try{
            /** 判断请求参数 */
            if (map != null && map.size() > 0){
                /** 获取签名参数 */
                String sign = map.remove("sign");

                String paramsStr = objectMapper.writeValueAsString(map);
                if (DigestUtils.md5Hex(SIGN_KEY + paramsStr).equals(sign)){
                    Item item = objectMapper.readValue(paramsStr, Item.class);
                    item.setCreated(new Date());
                    item.setUpdated(item.getCreated());
                    itemService.saveSelective(item);
                    /** 代表创建成功，201 */
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }

            }
            /** 请求参数有问题，错误请求 400 */
            return ResponseEntity.badRequest().build();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        /** 服务器内容错误 500 */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /** 修改商品 */
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody Map<String, String> map){
        try{
            /** 判断请求参数 */
            if (map != null && map.size() > 0){
                /** 获取签名参数 */
                String sign = map.remove("sign");
                /** 获取请求参数 */
                String paramsStr = objectMapper.writeValueAsString(map);
                if (DigestUtils.md5Hex(SIGN_KEY + paramsStr).equals(sign)){
                    Item item = objectMapper.readValue(paramsStr, Item.class);
                    item.setUpdated(new Date());
                    itemService.updateSelective(item);
                    /** 代表修改成功，没有响应内容返回  204 */
                    return ResponseEntity.noContent().build();
                }
            }
            /** 请求参数有问题，错误请求 400 */
            return ResponseEntity.badRequest().build();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        /** 服务器内容错误 500 */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /** 删除商品 */
    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@RequestParam("id") Long id,
                                           @RequestParam("sign") String sign){
        try{
            /** 判断请求参数 */
            if (id != null && id > 0){
                /** 判断参数签名 */
                if (DigestUtils.md5Hex(SIGN_KEY + id).equals(sign)){
                    itemService.delete(id);
                    /** 代表删除成功，没有响应内容返回  204 */
                    return ResponseEntity.noContent().build();
                }
            }
            /** 请求参数有问题，错误请求 400 */
            return ResponseEntity.badRequest().build();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        /** 服务器内容错误 500 */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
