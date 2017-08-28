package com.taotao.admin.service.redis.impl;

import com.taotao.admin.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

/**
 * 集群版实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年8月28日 上午11:59:43
 * @version 1.0
 */
public class JedisClusterServiceImpl implements RedisService {
	/** 注入JedisCluster连接池对象 */
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 设置键对应的值
	 * @param key 键
	 * @param value 值
	 * @return 状态码
	 */
	public String set(String key, String value){
		return jedisCluster.set(key, value);
	}
	/**
	 * 设置键对应的值 (生存时间)
	 * @param key 键
	 * @param value 值
	 * @param seconds 秒数
	 * @return 状态码
	 */
	public String setex(String key, String value, int seconds){
		return jedisCluster.setex(key, seconds, value);
	}
	/**
	 * 通过键获取指定的值
	 * @param key 键
	 * @return 值
	 */
	public String get(String key){
		return jedisCluster.get(key);
	}
	/**
	 * 根据键删除指定的值
	 * @param key 键
	 * @return 状态码
	 */
	public Long del(String key){
		return jedisCluster.del(key);
	}
	/**
	 * 设置键的生存时间
	 * @param key 键
	 * @param seconds 秒
	 * @return 状态码
	 */
	public Long expire(String key, int seconds){
		return jedisCluster.expire(key, seconds);
	}
	/**
	 * 获取一个键自增长的值
	 * @param key 键
	 * @return 自增长的值
	 */
	public Long incr(String key){
		return jedisCluster.incr(key);
	}
}