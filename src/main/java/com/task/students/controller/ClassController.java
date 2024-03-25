package com.task.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.students.entity.ClassEntity;
import com.task.students.service.ClassService;

@RestController
@RequestMapping("/api/class")
public class ClassController {
	@Autowired
	ClassService classService;
	
	@GetMapping("/getAllClasses")
	public ResponseEntity<List<ClassEntity>> getAllClasses() {
	    List<ClassEntity> classEntities = classService.getAll();
	    return ResponseEntity.ok().body(classEntities);
	}
}
