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
@Table(name="benefits")
public class Benefit extends BaseEntity{

	
	@Column(name="type")
	private String type;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="user_benefits",
			joinColumns=@JoinColumn(name="benefits_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	private List<User> users;
	
	public Benefit() {
		
	}

	public Benefit(String type, List<User> users) {
		this.type = type;
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
