package com.taotao.admin.service.impl;

import com.taotao.admin.pojo.ItemDesc;
import com.taotao.admin.service.ItemDescService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.taotao.admin.service.impl
 * @Description:
 * @Author: LYC
 * @Date: Created in 19:162017/8/24
 * @Modified By:
 */
@Service
@Transactional(readOnly = false)
public class ItemDescServiceImpl extends BaseServiceImpl<ItemDesc> implements ItemDescService {
}
