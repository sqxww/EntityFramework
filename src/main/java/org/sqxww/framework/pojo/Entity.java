package org.sqxww.framework.pojo;

import java.util.List;

/* 实体类
 * @author 李志伟
 * @date 2017年7月23日 
 */

public class Entity {
	private String entityId;
	private String tableName;
	private List<Field> fields;
	private List<Criterion> criterions;
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public List<Criterion> getCriterions() {
		return criterions;
	}
	public void setCriterions(List<Criterion> criterions) {
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
