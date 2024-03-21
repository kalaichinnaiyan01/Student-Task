package com.task.students.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.students.entity.StudentsEntity;
import com.task.students.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@PostMapping("/saveStudent")
	public String saveStudent(@RequestBody StudentsEntity entity) {
		return service.saveStudent(entity);
	}
	
	@GetMapping("/get/{id}")
	public Optional<StudentsEntity> getStudent(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/get")
	public List<StudentsEntity> getAllStudent() {
		return service.get();
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestBody StudentsEntity entity) {
		return service.updateStudent(entity);
		
	}
	@PutMapping("/{studentId}/enable")
    public ResponseEntity<Void> enableStudent(@PathVariable Long studentId) throws NotFoundException {
		service.enableStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}/disable")
    public ResponseEntity<Void> disableStudent(@PathVariable Long studentId) throws NotFoundException {
    	service.disableStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id) {
		return service.deleteUser(id);
		
	}
}
