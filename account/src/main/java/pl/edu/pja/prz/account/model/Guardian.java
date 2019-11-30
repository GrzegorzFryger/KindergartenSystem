package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.ChildId;

import java.util.List;

public class Guardian extends Account {

	List<ChildId> childrenList;

	public Guardian() {
		super();
	}

	public List<ChildId> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ChildId> childrenList) {
		this.childrenList = childrenList;
	}
}
