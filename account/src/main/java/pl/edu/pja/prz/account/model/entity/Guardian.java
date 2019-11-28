package pl.edu.pja.prz.account.model.entity;

import pl.edu.pja.prz.account.model.value.ChildId;

import java.util.List;

public class Guardian extends Account {

	List<ChildId> childrenList;

	public Guardian() {
	}

	public List<ChildId> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ChildId> childrenList) {
		this.childrenList = childrenList;
	}
}
