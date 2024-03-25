package com.task.students.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.students.entity.ClassEntity;
import com.task.students.entity.StudentsEntity;
@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

}
