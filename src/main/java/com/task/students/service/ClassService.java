package com.task.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.students.entity.ClassEntity;
import com.task.students.repositary.ClassRepository;

@Service
public class ClassService {
	
	@Autowired
	ClassRepository classRepository;

	public List<ClassEntity> getAll() {
		return  classRepository.findAll();
		
		
	}

}
