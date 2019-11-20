package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.ChildrenId;

import java.util.List;

public class Guardian extends Account {

	List<ChildrenId> childrenList;

	public Guardian() {
	}

	public List<ChildrenId> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ChildrenId> childrenList) {
		this.childrenList = childrenList;
	}
}
