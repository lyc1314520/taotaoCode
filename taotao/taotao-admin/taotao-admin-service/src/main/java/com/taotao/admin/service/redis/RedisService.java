package com.taotao.admin.service.redis;
/**
 * Redis服务接口类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年8月28日 上午11:53:11
 * @version 1.0
 */
public interface RedisService {
	/**
	 * 设置键对应的值
	 * @param key 键
	 * @param value 值
	 * @return 状态码
	 */
	String set(String key, String value);
	/**
	 * 设置键对应的值 (生存时间)
	 * @param key 键
	 * @param value 值
	 * @param seconds 秒数
	 * @return 状态码
	 */
	String setex(String key, String value, int seconds);
	/**
	 * 通过键获取指定的值
	 * @param key 键
	 * @return 值
	 */
	String get(String key);
	/**
	 * 根据键删除指定的值
	 * @param key 键
	 * @return 状态码
	 */
	Long del(String key);
	/**
	 * 设置键的生存时间
	 * @param key 键
	 * @param seconds 秒
	 * @return 状态码
	 */
	Long expire(String key, int seconds);
	/**
	 * 获取一个键自增长的值
	 * @param key 键
	 * @return 自增长的值
	 */
	Long incr(String key);
}