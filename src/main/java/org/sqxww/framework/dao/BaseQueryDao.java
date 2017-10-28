package org.sqxww.framework.dao;

import java.util.List;
import java.util.Map;

import org.sqxww.framework.pojo.Entity;

/**
 * action:
 * author:李志伟
 * date:2017年10月26日
 */

public interface BaseQueryDao {
	/**
	 * 获取单个实体
	 * @param sql
	 * @param params
	 * @return
	 */
	public Entity get(String sql, Map<String, Object> params);
	
	/**
	 * 获取多个实体
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Entity> find(String sql, Map<String, Object> params);
}
