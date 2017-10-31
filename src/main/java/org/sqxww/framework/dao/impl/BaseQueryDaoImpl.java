package org.sqxww.framework.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.sqxww.framework.dao.BaseQueryDao;
import org.sqxww.framework.dao.mapper.EntityMapper;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public class BaseQueryDaoImpl implements BaseQueryDao {
	
	private NamedParameterJdbcTemplate nameTemplate = null;

	@Override
	public Entity get(String sql, Map<String, Field> fields, Map<String, Object> params) {
		EntityMapper mapper = new EntityMapper(fields);
		return nameTemplate.queryForObject(sql, params, mapper);
	}

	@Override
	public List<Entity> find(String sql, Map<String, Field> fields, Map<String, Object> params) {
		EntityMapper mapper = new EntityMapper(fields);
		return nameTemplate.query(sql, params, mapper);
	}

}
