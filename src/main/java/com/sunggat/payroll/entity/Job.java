package com.sunggat.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="job")
public class Job extends BaseEntity {
	
	@Column(name="job_title")
	public String title;
	
	public Job() {
		
	}

	public Job(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
