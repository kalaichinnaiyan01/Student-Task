package com.task.students.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_classes")
@Getter
@Setter
public class ClassEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
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
