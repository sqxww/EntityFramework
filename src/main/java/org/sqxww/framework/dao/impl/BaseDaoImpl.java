package org.sqxww.framework.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.sqxww.framework.dao.BaseDao;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public class BaseDaoImpl implements BaseDao {
	
	private NamedParameterJdbcTemplate namedTemplate;

	@Override
	public int insert(String sql, Map<String, Object> params) {
		return namedTemplate.update(sql, params);
	}

	@Override
	public int update(String sql, Map<String, Object> params) {
		return namedTemplate.update(sql, params);
	}

	@Override
	public int delete(String sql, Map<String, Object> params) {
		return namedTemplate.update(sql, params);
	}

}
