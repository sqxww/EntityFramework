package org.sqxww.framework.pojo;

import java.util.Map;
/* 或条件
 * @author lizhiwei
 * @date 2017年7月24日
 */
public class Criterion {
	private Map<String, Criteria> criterias;
	private boolean required = false;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Map<String, Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(Map<String, Criteria> criterias) {
		this.criterias = criterias;
	}
}
