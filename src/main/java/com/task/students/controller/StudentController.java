package com.task.students.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.task.students.entity.StudentsEntity;
import com.task.students.service.StudentService;
import com.task.students.util.FlyingSaucerPDFUtil;



//@RestController
//@RequestMapping("/api/student")
@Controller
public class StudentController {

	@Autowired
	StudentService service;
	
	@Autowired
	private FlyingSaucerPDFUtil flyingSaucerPDFUtil;
	
	@GetMapping("/student")
	public String getAllStudents(Model model) {
		List<StudentsEntity> studentDetails = service.get();
        studentDetails.sort(Comparator.comparing(StudentsEntity::getId));
		model.addAttribute("showStudentDetails", studentDetails);
		return "student";
	}

	@GetMapping("/addStudentForm")
	public String addStudentForm(Model model) {
		model.addAttribute("addStudent", new StudentsEntity());
		return "add-student";
	}
	
	@GetMapping("/generatePdf")
    public ResponseEntity<?> generatePdfForStudentDownload() {
        List<StudentsEntity> students = service.getAllStudentsList();
        students.sort(Comparator.comparing(StudentsEntity::getId));
        Map<String, Object> response = new HashMap<>();
        response.put("students", students);

        try {
        	byte[] report = flyingSaucerPDFUtil.generatePdf("studentpdf", response);
			InputStream excelInputStream = new ByteArrayInputStream(report);
			return ResponseEntity.ok().header("Access-Control-Expose-Headers", "Content-Disposition")
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header("Content-Disposition", "attachment; filename= Student.pdf")
					.body(new InputStreamResource(excelInputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating PDF");
        }
    }
	
	@GetMapping("/generateXl")
    public ResponseEntity<?> generateXlForStudentDownload() {
        List<StudentsEntity> students = service.getAllStudentsList();
        students.sort(Comparator.comparing(StudentsEntity::getId));
        Map<String, Object> response = new HashMap<>();
        response.put("students", students);

        try {
		    Resource resource = new ClassPathResource("templates/student.xlsx");
		    InputStream fileStream = resource.getInputStream();
		    XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		    XSSFSheet worksheet = workbook.getSheetAt(0);
		    XSSFCellStyle style1 = workbook.createCellStyle();
		    XSSFFont font = workbook.createFont();
		    font.setBold(true);

		    for (int i = 0; i < students.size(); i++) {
		        XSSFRow row = worksheet.createRow(i + 1);
		        row.createCell(0).setCellValue(students.get(i).getId());
		        row.createCell(1).setCellValue(students.get(i).getClassId());
		        row.createCell(2).setCellValue(students.get(i).getClassTeacherId());
		        row.createCell(3).setCellValue(students.get(i).getName());
		        row.createCell(4).setCellValue(students.get(i).getDateOfBirth());
		        row.createCell(5).setCellValue(students.get(i).getGender().toString());
		        row.createCell(6).setCellValue(students.get(i).getAddressLine1());
		        row.createCell(7).setCellValue(students.get(i).getCity());
		    }

		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    workbook.write(bos);
		    workbook.close();
		    byte[] barray = bos.toByteArray();
		    InputStream excelInputStream = new ByteArrayInputStream(barray);

		    String fileName = "student.xlsx";

		    return ResponseEntity.ok().header("Access-Control-Expose-Headers", "Content-Disposition")
		            .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
		            .body(new InputStreamResource(excelInputStream));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating XL");
        }
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
