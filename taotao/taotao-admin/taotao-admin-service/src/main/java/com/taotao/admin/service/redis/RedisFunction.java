package com.taotao.admin.service.redis;

import redis.clients.jedis.Jedis;

/**
 * RedisFunction
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年8月28日 下午12:05:15
 * @version 1.0
 */
public interface RedisFunction<T> {
	
	/** 回调方法 */
	T callback(Jedis jedis);
}
