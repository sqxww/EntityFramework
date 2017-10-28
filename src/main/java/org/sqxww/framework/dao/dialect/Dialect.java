package org.sqxww.framework.dao.dialect;

import java.util.List;
import java.util.Map;

import org.sqxww.framework.pojo.Entity;

/**
 * action:
 * author:李志伟
 * date:2017年10月27日
 */

public interface Dialect {
	public String insert(Entity entity, Map<String, Object> params);
	public String delete(Entity entity, Map<String, Object> params);
	public String update(Entity entity, Map<String, Object> params);
	public String batchInsert(List<Entity> entities, Map<String, Object> params);
	public String batchDelete(List<Entity> entities, Map<String, Object> params);
	public String batchUpdate(List<Entity> entities, Map<String, Object> params);
}
