package org.sqxww.framework.dao.dialect.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.sqxww.framework.dao.dialect.Dialect;
import org.sqxww.framework.pojo.Criteria;
import org.sqxww.framework.pojo.Criterion;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public class MySqlDialect implements Dialect {

	@Override
	public String insert(Entity entity, Map<String, Object> params) {
		Map<String, Field> fields = entity.getFields();
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ").append(entity.getTableName()).append(" ( ");
		StringBuilder  valueSb = new StringBuilder();
		for(Entry<String, Field> entry : fields.entrySet()){
			Field field = entry.getValue();
			sb.append(field.getFieldName()).append(",");
			//字段别名作为入参占位符
			valueSb.append( ":").append(entry.getKey()).append(",");
			if(field.getValue() != null) {
				params.put(entry.getKey(), field.getValue());
			}else {
				params.put(entry.getKey(), field.getDefaulValue());
			}
		}
		sb.deleteCharAt(sb.length() - 1).append(") values (")
			.append(valueSb.deleteCharAt(valueSb.length() - 1)).append(")");
		String sql = sb.toString();
		return sql;
	}

	@Override
	public String delete(Entity entity, Map<String, Object> params) {
		Map<String, Criterion> criterions = entity.getCriterions();
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(entity.getTableName()).append(" where")
			.append(getSqlStrByCriterion(criterions, params));
		String sql = sb.toString();
		return sql;
	}

	@Override
	public String update(Entity entity, Map<String, Object> params) {
		Map<String, Field> fields = entity.getFields();
		Map<String, Criterion> criterions = entity.getCriterions();
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(entity.getTableName()).append(" set ")
			.append(getUpdateSqlStr(fields, params)).append(" where ")
			.append(getSqlStrByCriterion(criterions, params));
		String sql = sb.toString();
		return sql;
	}

	@Override
	public String batchInsert(List<Entity> entities, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String batchDelete(List<Entity> entities, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String batchUpdate(List<Entity> entities, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * sql过滤语句
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getSqlStrByCriterion(Map<String, Criterion> criterions, Map<String, Object> params){
		//(sb是在方法体中声明且仅在此方法体中使用，相当于每个sb仅在单线程中使用)
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Criterion> entry : criterions.entrySet()){
			Criterion criterion = entry.getValue();
			sb.append(" (");
			for(Entry<String, Criteria> criteriaEntry : criterion.getCriterias().entrySet()){
				Criteria criteria = criteriaEntry.getValue();
				sb.append(" ");
				sb.append(criteria.getFieldName());
				String operation = criteria.getOperation();
				//单值入参时，直接使用别名来充当占位符
				if("=".equals(operation) || "<>".equals(operation) || ">=".equals(operation)
						|| ">".equals(operation) || "<".equals(operation) || "<=".equals(operation)
						|| "like".equals(operation)){
					sb.append(operation).append(" :").append(criteriaEntry.getKey());
					params.put(criteriaEntry.getKey(), criteria.getValue());
				//多值入参时，使用别名加索引来充当占位符
				} else if("between".equals(operation) || "not between".equals(operation)){
					sb.append(" ").append(operation).append(" :").append(criteriaEntry.getKey())
						.append("_1").append(" and :").append(criteriaEntry.getKey()).append("_2");
					List<Object> values = null;
					if (criteria.getValue() != null) {
						values = (List<Object>) criteria.getValue();
					}else {
						values = (List<Object>) criteria.getDefaultValue();
					}
					params.put(criteriaEntry.getKey()+"_1", values.get(0));
					params.put(criteriaEntry.getKey()+"_2", values.get(1));
				} else if("in".equals(operation) || "not in".equals(operation)){
					sb.append(" ").append(operation).append("(");
					List<Object> values = null;
					if(criteria.getValue() != null) {
						values = (List<Object>) criteria.getValue();
					}else {
						values = (List<Object>) criteria.getDefaultValue();
					}
					for(int i = 0; i < values.size(); i++) {
						Object value = values.get(i);
						sb.append(":").append(criteriaEntry.getKey())
							.append("_").append(i+1).append(",");
						params.put(entry.getKey() + "_" + (i+1), value);
					}
					sb.deleteCharAt(sb.length() - 1).append(")");
				} else{
//					logger.error("错误的操作符" + operation);
				}
				sb.append(" and");
			}
			sb.delete(sb.length() - 3, sb.length());
			sb.append(") or");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
	
	/**
	 * 更新的赋值语句
	 * @param fields
	 * @return
	 */
	private String getUpdateSqlStr(Map<String, Field> fields, Map<String, Object> params){
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Field> entry : fields.entrySet()){
			Field field = entry.getValue();
			//使用字段别名作为入参占位符
			sb.append(field.getFieldName()).append("=:").append(entry.getKey());
			sb.append(",");
			if (field.getValue() != null) {
				params.put(entry.getKey(), field.getValue());
			}else {
				params.put(entry.getKey(), field.getDefaulValue());
			}
		}
		sb.deleteCharAt(sb.length() - 1).append(" ");
		return sb.toString();
	}

}
