package org.sqxww.framework.utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.sqxww.framework.pojo.Criteria;
import org.sqxww.framework.pojo.Criterion;
import org.sqxww.framework.pojo.Entity;
import org.sqxww.framework.pojo.Field;

/* 生成sql语句工具类
 * @author lizhiwei
 * @date 2017年7月24日
 */
public class SqlUtil {
	private static final Logger logger = (Logger) LogManager.getLogger();
	
	/**
	 * 查询语句的生成
	 * @param entityId
	 * @return
	 */
	public static String selectSql(String entityId){
		Entity entity = (Entity) ApplicationContextUtil.getBeanByname(entityId);
		List<Field> fields = entity.getFields();
		List<Criterion> criterions = entity.getCriterions();
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		for(Field field : fields){
			sb.append(field.getFieldName()).append(" as ")
				.append(field.getLabelName()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" from ").append(entity.getTableName())
			.append(" where ").append(getSqlStrByCriterion(criterions));
		String sql = sb.toString();
		logger.info("entityId:" + entityId + "生成的select语句为" + sql);
		return sql;
	}
	
	/**
	 * 更新语句的生成
	 * @param entityId
	 * @return
	 */
	public static String getUpdateSql(String entityId){
		Entity entity = (Entity) ApplicationContextUtil.getBeanByname(entityId);
		List<Field> fields = entity.getFields();
		List<Criterion> criterions = entity.getCriterions();
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(entity.getTableName()).append(" set ")
			.append(getUpdateSqlStr(fields)).append(" where ")
			.append(getSqlStrByCriterion(criterions));
		String sql = sb.toString();
		logger.info("entityId:" + entityId + "生成的update语句为" + sql);
		return sql;
	}
	
	/**
	 * 插入语句的生成
	 * @param entityId
	 * @return
	 */
	public static String getInsertSql(String entityId){
		Entity entity = (Entity) ApplicationContextUtil.getBeanByname(entityId);
		List<Field> fields = entity.getFields();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ").append(entity.getTableName()).append(" ( ");
		String  valuesStr = "";
		for(Field field : fields){
			sb.append(field.getFieldName()).append(",");
			//字段别名作为入参占位符
			valuesStr += ":" + field.getLabelName() + ",";
		}
		sb.deleteCharAt(sb.length() - 1).append(") ").append("values (")
			.append(valuesStr.substring(0, valuesStr.length() - 1)).append(")");
		String sql = sb.toString();
		logger.info("entityId:" + entityId + "生成的insert语句为" + sql);
		return sql;
	}
	
	/**
	 * sql过滤语句
	 * @param criterions
	 * @return
	 */
	private static String getSqlStrByCriterion(List<Criterion> criterions){
		//可考虑使用StringBuilder替换来提升速度
		//(sb是在方法体中声明且仅在此方法体中使用，相当于每个sb仅在单线程中使用)
		StringBuffer sb = new StringBuffer();
		for(Criterion criterion : criterions){
			sb.append(" (");
			for(Criteria criteria : criterion.getCriterias()){
				sb.append(" ");
				sb.append(criteria.getFieldName());
				String operation = criteria.getOperation();
				//单值入参时，直接使用别名来充当占位符
				if("=".equals(operation) || "<>".equals(operation) || ">=".equals(operation)
						|| ">".equals(operation) || "<".equals(operation) || "<=".equals(operation)
						|| "like".equals(operation)){
					sb.append(operation).append(" :").append(criteria.getLabelName());
				//多值入参时，使用别名加索引来充当占位符
				} else if("between".equals(operation) || "not between".equals(operation)){
					sb.append(" ").append(operation).append(" :").append(criteria.getLabelName())
						.append("_1").append(" and :").append(criteria.getLabelName()).append("_2");
				//需根据参数个数来拼接，故暂时使用占位符${dynamicParams}标识
				} else if("in".equals(operation) || "not in".equals(operation)){
					sb.append(" ").append(operation).append("${dynamicParams}");
				} else{
					logger.error("错误的操作符" + operation);
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
	private static String getUpdateSqlStr(List<Field> fields){
		StringBuffer sb = new StringBuffer();
		for(Field field : fields){
			//使用字段别名作为入参占位符
			sb.append(field.getFieldName()).append("=:").append(field.getLabelName());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(" ");
		return sb.toString();
	}
}
