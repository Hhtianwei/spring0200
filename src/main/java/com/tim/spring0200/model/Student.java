package com.tim.spring0200.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="student")
public class Student {

	private int id;
	
	private String name;
	
	private int age;
	
	private String code;
	
	@Id 
    @Column(name="id", unique = true, nullable = false)  
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	@Column(name="name", unique = false, nullable = false)  
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="age", unique = false, nullable = false)  
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name="code", unique = false, nullable = true)  
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
