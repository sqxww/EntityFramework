package org.sqxww.framework.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.sqxww.framework.pojo.Criteria;
import org.sqxww.framework.pojo.Criterion;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/**
 * action:
 * author:李志伟
 * date:2017年10月28日
 */

public class EntityUtil {
	
	public static Entity getEntity(Entity requestEntity) {
		Entity entityTemplate = (Entity) ApplicationContextUtil.getBeanByname(requestEntity.getEntityId());
		setFields(requestEntity, entityTemplate);
		setCriterions(requestEntity, entityTemplate);
		return requestEntity;
	}
	
	private static void setFields(Entity requestEntity, Entity entityTemplate) {
		Map<String, Field> requestFields = requestEntity.getFields();
		Map<String, Field> templateFields = entityTemplate.getFields();
		for(Entry<String, Field> entry : templateFields.entrySet()) {
			Field templateField = entry.getValue();
			Field field = requestFields.get(entry.getKey());
			if(null == field) {
				if(templateField.isRequired())
					//TODO 统一抛出异常
					throw new RuntimeException("");
				if(null != templateField.getDefaulValue()) {
					requestFields.put(entry.getKey(), templateField);
				}
			}else {
				field.setFieldName(templateField.getFieldName());
				field.setSqlType(templateField.getSqlType());
			}
		}
	}
	
	private static void setCriterions(Entity requestEntity, Entity entityTemplate) {
		Map<String, Criterion> requestCriterions = requestEntity.getCriterions();
		Map<String, Criterion> templateCriterions = entityTemplate.getCriterions();
		for(Entry<String, Criterion>  entry : templateCriterions.entrySet()) {
			Criterion templateCriterion = entry.getValue();
			Criterion criterion = requestCriterions.get(entry.getKey());
			if(null == criterion) {
				if(templateCriterion.isRequired())
					//TODO 统一抛出异常
					throw new RuntimeException("");
			}else {
				setCriterias(criterion, templateCriterion);
			}
		}
	}
	
	private static void setCriterias(Criterion requestCriterion, Criterion criterionTemplate) {
		Map<String, Criteria> templateCriterias = criterionTemplate.getCriterias();
		Map<String, Criteria> requestCriterias = requestCriterion.getCriterias();
		for(Entry<String, Criteria> entry : templateCriterias.entrySet()) {
			Criteria templateCriteria = entry.getValue();
			Criteria criteria = requestCriterias.get(entry.getKey());
			if(null == criteria) {
				if(templateCriteria.isRequired())
					//TODO 统一抛出异常
					throw new RuntimeException("");
				if(null != templateCriteria.getDefaultValue()) {
					requestCriterias.put(entry.getKey(), templateCriteria);
				}
			}else {
				criteria.setFieldName(templateCriteria.getFieldName());
				criteria.setOperation(templateCriteria.getOperation());
			}
		}
	}
	
}
