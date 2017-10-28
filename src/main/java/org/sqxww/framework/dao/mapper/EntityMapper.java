package org.sqxww.framework.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public class EntityMapper implements RowMapper<Entity> {

	@Override
	public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Field> fields = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i = 1; i <= rsmd.getColumnCount(); i++) {
			Field field = new Field();
			field.setLabelName(rsmd.getColumnLabel(i));
			//TODO 返回值转换
//			field.setValue(rs.g);
			fields.add(field);
		}
		Entity entity = new Entity();
//		entity.setFields(fields);
		return entity;
	}

}
