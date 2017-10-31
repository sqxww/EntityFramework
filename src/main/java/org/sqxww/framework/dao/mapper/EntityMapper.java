package org.sqxww.framework.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public class EntityMapper implements RowMapper<Entity> {
	
	private Map<String, Field> fields;
	
	public EntityMapper(Map<String, Field> fields) {
		this.fields = fields;
	}

	@Override
	public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Field> fields = new HashMap<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i = 1; i <= rsmd.getColumnCount(); i++) {
			Field field = new Field();
			String labelName = rsmd.getColumnLabel(i);
			String key = "field_" + labelName;
			field.setLabelName(labelName);
			if(this.fields == null) {
				int type = rsmd.getColumnType(i);
				field.setValue(getValue(type, rs, i));
			}
			else {
				Field templateField = this.fields.get(key);
				if(null != templateField) {
					String javaType = templateField.getJavaType();
					field.setValue(getValue(javaType, rs, i));
				}else {
					//TODO 抛异常
				}
			}
			fields.put(key, field);
		}
		Entity entity = new Entity();
		entity.setFields(fields);
		return entity;
	}
	
	private Object getValue(int type, ResultSet rs, int columnIndex) throws SQLException {
		switch (type) {
		case Types.ARRAY:
			return rs.getArray(columnIndex);
		case Types.BIGINT:
			return rs.getLong(columnIndex);
		case Types.BINARY:
			return rs.getBytes(columnIndex);
		case Types.BIT:
			return rs.getBoolean(columnIndex);
		case Types.BLOB:
			return rs.getBytes(columnIndex);
		case Types.BOOLEAN:
			return rs.getBoolean(columnIndex);
		case Types.CHAR:
			return rs.getString(columnIndex);
		case Types.CLOB:
			return rs.getClob(columnIndex);
		case Types.DATALINK:
			return rs.getURL(columnIndex);
		case Types.DATE:
			return rs.getDate(columnIndex);
		case Types.DECIMAL:
			return rs.getBigDecimal(columnIndex);
		case Types.DISTINCT:
			return null;
		case Types.DOUBLE:
			return rs.getDouble(columnIndex);
		case Types.FLOAT:
			return rs.getFloat(columnIndex);
		case Types.INTEGER:
			return rs.getInt(columnIndex);
		case Types.JAVA_OBJECT:
			return rs.getObject(columnIndex);
		case Types.LONGNVARCHAR:
			return rs.getString(columnIndex);
		case Types.LONGVARBINARY:
			return rs.getString(columnIndex);
		case Types.LONGVARCHAR:
			return rs.getString(columnIndex);
		case Types.NCHAR:
			return rs.getString(columnIndex);
		case Types.NCLOB:
			return rs.getNClob(columnIndex);
		case Types.NULL:
			return rs.getObject(columnIndex);
		case Types.NUMERIC:
			return rs.getBigDecimal(columnIndex);
		case Types.NVARCHAR:
			return rs.getString(columnIndex);
		case Types.OTHER:
			return rs.getObject(columnIndex);
		case Types.REAL:
			return rs.getObject(columnIndex);
		case Types.REF:
			return rs.getRef(columnIndex);
		case Types.ROWID:
			return rs.getInt(columnIndex);
		case Types.SMALLINT:
			return rs.getInt(columnIndex);
		case Types.SQLXML:
			return rs.getSQLXML(columnIndex);
		case Types.STRUCT:
			return rs.getObject(columnIndex);
		case Types.TIME:
			return rs.getTime(columnIndex);
		case Types.TIMESTAMP:
			return rs.getTimestamp(columnIndex);
		case Types.TINYINT:
			return rs.getShort(columnIndex);
		case Types.VARBINARY:
			return rs.getString(columnIndex);
		case Types.VARCHAR:
			return rs.getString(columnIndex);

		default:
			break;
		}
		return null;
	}
	
	private Object getValue(String javaType,ResultSet rs, int columnIndex) throws SQLException {
		switch (javaType) {
		case "java.sql.Array":
			return rs.getArray(columnIndex);
		case "java.io.InputStream":
			return rs.getBinaryStream(columnIndex);
		case "java.math.BigDecimal":
			return rs.getBigDecimal(columnIndex);
		case "java.sql.Blob":
			return rs.getBlob(columnIndex);
		case "java.lang.Boolean":
			return rs.getBoolean(columnIndex);
		case "java.lang.Byte":
			return rs.getByte(columnIndex);
		case "java.lang.Byte[]":
			return rs.getByte(columnIndex);
		case "java.sql.Clob":
			return rs.getClob(columnIndex);
		case "java.sql.Date":
			return rs.getDate(columnIndex);
		case "java.lang.Double":
			return rs.getDouble(columnIndex);
		case "java.lang.Float":
			return rs.getFloat(columnIndex);
		case "java.lang.Integer":
			return rs.getInt(columnIndex);
		case "java.lang.Long":
			return rs.getLong(columnIndex);
		case "java.sql.NClob":
			return rs.getNClob(columnIndex);
		case "java.lang.Object":
			return rs.getObject(columnIndex);
		case "java.sql.Ref":
			return rs.getRef(columnIndex);
		case "java.lang.Short":
			return rs.getShort(columnIndex);
		case "java.sql.SQLXML":
			return rs.getSQLXML(columnIndex);
		case "java.lang.String":
			return rs.getString(columnIndex);
		case "java.sql.Time":
			return rs.getTime(columnIndex);
		case "java.sql.TimeStamp":
			return rs.getTimestamp(columnIndex);
		case "java.netURL":
			return rs.getURL(columnIndex);
		default:
			break;
		}
		return null;
	}

}
