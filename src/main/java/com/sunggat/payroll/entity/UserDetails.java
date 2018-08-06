package com.sunggat.payroll.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_details")
public class UserDetails extends BaseEntity {

	@Column(name="join_date")
	@Temporal(TemporalType.DATE)
	public Date joinDate;
	
	@Column(name="position")
	public String position;
	
	@Column(name="experience")
	public String experience;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="userDetail", cascade=CascadeType.ALL)
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="job_id")
	private Job job;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="department_id")
	private Department department;
	
	public UserDetails() {
		
	}

	public UserDetails(Date joinDate, String position, String experience) {
		this.joinDate = joinDate;
		this.position = position;
		this.experience = experience;
	}

	public UserDetails(Date joinDate, String position, String experience, User user, Job job, Department department) {
		super();
		this.joinDate = joinDate;
		this.position = position;
		this.experience = experience;
		this.user = user;
		this.job = job;
		this.department = department;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
}
