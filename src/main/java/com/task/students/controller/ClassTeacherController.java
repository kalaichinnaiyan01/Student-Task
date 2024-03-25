package com.task.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.students.entity.ClassTeacherEntity;
import com.task.students.service.ClassTeacherService;

@RestController
@RequestMapping("/api/classteacher")
public class ClassTeacherController {
	@Autowired
	ClassTeacherService classTeacherService;
	
	@GetMapping("/getAllClasses/{classId}")
	public ResponseEntity<List<ClassTeacherEntity>> getAllClasses(@PathVariable Long classId) {
	    List<ClassTeacherEntity> classEntities = classTeacherService.getAll(classId);
	    return ResponseEntity.ok().body(classEntities);
	}
	
	

}
