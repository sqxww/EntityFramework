package org.sqxww.framework.dao;

import java.util.Map;

/**
 * action:
 * author:李志伟
 * date:2017年10月26日
 */

public interface BaseDao {
	public int insert(String sql, Map<String, Object> params);
	
	public int update(String sql, Map<String, Object> params);
	
	public int delete(String sql, Map<String, Object> params);
}
