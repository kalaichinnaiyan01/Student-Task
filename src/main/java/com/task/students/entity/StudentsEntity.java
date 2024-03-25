package com.task.students.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.students.enums.GenderType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_students")
@Getter
@Setter
public class StudentsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="class_id")
	private Long classId;
	
	@Column(name="class_teacher_id")
	private Long classTeacherId;
	
	@Column(name="name")
	private String name;
	
//	@JsonFormat(pattern = "yyyy/MM/dd")
	@Column(name="data_of_brith")
	private String dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private GenderType gender;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="about_me")
	private String aboutMe;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@CreatedDate
	@Column(name = "created_at",nullable = false, updatable = false)
	private LocalDateTime createdAt; 
	
	@CreatedBy
	@Column(name = "created_by", nullable = false)
	private long createdBy; 
	
	@LastModifiedDate
	@Column(name = "updated_at",nullable = false, updatable = false)
	private LocalDateTime updatedAt; 
	
	@LastModifiedBy
	@Column(name = "updated_by", nullable = false)
	private long updatedBy; 

}
