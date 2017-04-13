package com.tim.spring0200.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tim.spring0200.common.dao.CommonDAO;
import com.tim.spring0200.model.Student;
import com.tim.spring0200.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private CommonDAO commonDAO;
	
	@Override
	public void save(Student student) {
		commonDAO.saveOrUpdateEntity(student);
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	@Transactional
	public void saveTest(List<Student> list) {
		
		for(Student s:list){
			if(s.getName().equals("tim-40")){
				throw new NullPointerException("test test test");
			}
			this.save(s);
		}
	}

}
