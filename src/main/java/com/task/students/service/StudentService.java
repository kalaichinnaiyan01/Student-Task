package com.task.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.task.students.entity.StudentsEntity;

@Service
public interface StudentService {
	
	
	public String saveStudent(StudentsEntity entity);

	public Optional<StudentsEntity> getById(Long id);

	public List<StudentsEntity> get();

	public String updateStudent(StudentsEntity entity);
	
	public void enableStudent(Long studentId) throws NotFoundException;
	
    public void disableStudent(Long studentId) throws NotFoundException;

	public String deleteUser(Long id);

}
