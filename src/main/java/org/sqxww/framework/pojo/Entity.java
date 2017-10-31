package org.sqxww.framework.pojo;

import java.util.Map;

/* 实体类
 * @author 李志伟
 * @date 2017年7月23日 
 */

public class Entity {
	private String entityId;
	private String tableName;
	private Map<String, Field> fields;
	private Map<String, Criterion> criterions;
	public Map<String, Field> getFields() {
		return fields;
	}
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	public Map<String, Criterion> getCriterions() {
		return criterions;
	}
	public void setCriterions(Map<String, Criterion> criterions) {
		this.criterions = criterions;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
