package com.taotao.admin.service.redis.impl;

import com.taotao.admin.service.redis.RedisFunction;
import com.taotao.admin.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 单机版实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年8月28日 上午11:59:43
 * @version 1.0
 */
public class JedisPoolServiceImpl implements RedisService {
	/** 注入JedisPool连接池对象 */
	@Autowired
	private JedisPool jedisPool;
	
	/** 定义执行的方法 */
	private <T> T execute(RedisFunction<T> redisFunction){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return redisFunction.callback(jedis);
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 设置键对应的值
	 * @param key 键
	 * @param value 值
	 * @return 状态码
	 */
	public String set(final String key,final String value){
		return execute(new RedisFunction<String>() {
			@Override
			public String callback(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}
	/**
	 * 设置键对应的值 (生存时间)
	 * @param key 键
	 * @param value 值
	 * @param seconds 秒数
	 * @return 状态码
	 */
	public String setex(final String key, final String value,final int seconds){
		return execute(new RedisFunction<String>() {
			@Override
			public String callback(Jedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		});
	}
	/**
	 * 通过键获取指定的值
	 * @param key 键
	 * @return 值
	 */
	public String get(final String key){
		return execute(new RedisFunction<String>() {
			@Override
			public String callback(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}
	/**
	 * 根据键删除指定的值
	 * @param key 键
	 * @return 状态码
	 */
	public Long del(final String key){
		return execute(new RedisFunction<Long>() {
			@Override
			public Long callback(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}
	/**
	 * 设置键的生存时间
	 * @param key 键
	 * @param seconds 秒
	 * @return 状态码 
	 */
	public Long expire(final String key,final int seconds){
		return execute(new RedisFunction<Long>() {
			@Override
			public Long callback(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}
	/**
	 * 获取一个键自增长的值
	 * @param key 键
	 * @return 自增长的值
	 */
	public Long incr(final String key){
		return execute(new RedisFunction<Long>() {
			@Override
			public Long callback(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}
}