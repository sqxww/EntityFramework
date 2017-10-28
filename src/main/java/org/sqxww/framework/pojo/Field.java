package org.sqxww.framework.pojo;

/* 字段类
 * @author 李志伟
 * @date 2017年7月23日 
 */

public class Field {
	private String fieldName;
	private String labelName;
	private String sqlType;
	private Object defaulValue;
	private Object value;
	private boolean required = false;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public Object getDefaulValue() {
		return defaulValue;
	}
	public void setDefaulValue(Object defaulValue) {
		this.defaulValue = defaulValue;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
}
