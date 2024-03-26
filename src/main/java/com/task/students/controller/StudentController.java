package com.task.students.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.task.students.entity.StudentsEntity;
import com.task.students.service.StudentService;

//@RestController
//@RequestMapping("/api/student")
@Controller
public class StudentController {
	
	@Autowired
	StudentService service;
	
	 @GetMapping("/")
	    public String getAllStudents(Model model) {
	        model.addAttribute("showStudentDetails", service.get());
	        return "student";
	    }
	    
	    @GetMapping("/addStudentForm")
	    public String addStudentForm(Model model) {
	        model.addAttribute("addStudent", new StudentsEntity());
	        return "add-student";
	    }
	    
	    @PostMapping("/saveStudent")
	    public String saveStudent(StudentsEntity entity) {
	        service.saveStudent(entity);
	        return "redirect:/"; 
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
