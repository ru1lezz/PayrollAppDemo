package com.sunggat.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department extends BaseEntity {
	
	@Column(name="name")
	public String name;
	
	@Column(name="location")
	public String location;
	
	public Department() {
		
	}

	public Department(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
