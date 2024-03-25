package com.task.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.task.students.entity.ClassTeacherEntity;
import com.task.students.entity.StudentsEntity;
import com.task.students.repositary.ClassTeacherRepository;
@Service
public class ClassTeacherService {
@Autowired
ClassTeacherRepository classTeacherRepository;
	
	public List<ClassTeacherEntity> getAll(Long classId) {
		return classTeacherRepository.findByClassId(classId);
	}

}
