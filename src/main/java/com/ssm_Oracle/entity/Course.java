package com.ssm_Oracle.entity;

import javax.persistence.*;

@Table(name="KRRY_COURSE_TEST")
public class Course extends BaseEntity{
	
	@Column(name="ID",nullable=false,unique=true)
	private String id;
	@Column(name="CNAME",nullable=false,unique=true)
	private String CName;
	public String getId() {
		return id;
	}
	public String getCName() {
		return CName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", CName=" + CName + "]";
	}
	
	
	
	
	
}
