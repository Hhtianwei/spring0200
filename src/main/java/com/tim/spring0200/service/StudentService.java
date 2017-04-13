package com.tim.spring0200.service;

import java.util.List;

import com.tim.spring0200.model.Student;

public interface StudentService {
	
	void save(Student student);
	
	List<Student> getAllStudent();

	void saveTest(List<Student> list);

}
