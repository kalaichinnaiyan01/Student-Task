package com.task.students.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.students.entity.StudentsEntity;

public interface StudentRepository extends JpaRepository<StudentsEntity, Long>{

}
