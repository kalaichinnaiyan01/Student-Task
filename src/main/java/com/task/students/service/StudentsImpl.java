package com.task.students.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.students.entity.StudentsEntity;
import com.task.students.repositary.StudentRepository;

@Service
public class StudentsImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	@Override
	public String saveStudent(StudentsEntity entity) {
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		repository.save(entity);
		return "Saved Successfully";

	}

	@Override
	public Optional<StudentsEntity> getById(Long id) {
		return repository.findById(id);
		 
	}

	@Override
	public List<StudentsEntity> get() {
		return repository.findAll();
	}

	@Override
	public String updateStudent(StudentsEntity entity) {
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		repository.save(entity);
		return "Update Successfully";
	}

	@Override
    public void enableStudent(Long studentId) throws NotFoundException {
        // Additional logic for enabling a student
		StudentsEntity student = repository.findById(studentId)
            .orElseThrow(() -> new NotFoundException());
        student.setIsActive(true);
        repository.save(student);
    }

    @Override
    public void disableStudent(Long studentId) throws NotFoundException {
        // Additional logic for disabling a student
    	StudentsEntity student = repository.findById(studentId)
            .orElseThrow(() -> new NotFoundException());
        student.setIsActive(false);
        repository.save(student);
    }

	@Override
	public String deleteUser(@PathVariable Long id) {
		 repository.deleteById(id);
		return "Deleted Successfully";
		 
	}

	@Override
	public List<StudentsEntity> getAllStudentsList() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
