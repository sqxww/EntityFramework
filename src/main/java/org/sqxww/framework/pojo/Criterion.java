package org.sqxww.framework.pojo;

import java.util.List;
/* 或条件
 * @author lizhiwei
 * @date 2017年7月24日
 */
public class Criterion {
	private List<Criteria> criterias;

	public List<Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}
}
