package com.sunggat.payroll.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="performance")
public class Performance extends BaseEntity{

	@Column(name="criteria")
	public String criteria;
	
	@Column(name="mark")
	public int mark;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="user_performance",
			joinColumns=@JoinColumn(name="performance_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	public List<User> users;
	
	public Performance() {
		
	}

	public Performance(String criteria, int mark) {
		this.criteria = criteria;
		this.mark = mark;
	}

	public Performance(String criteria, int mark, List<User> users) {
		super();
		this.criteria = criteria;
		this.mark = mark;
		this.users = users;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
