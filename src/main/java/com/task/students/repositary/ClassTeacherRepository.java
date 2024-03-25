package com.task.students.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.students.entity.ClassTeacherEntity;


@Repository
public interface ClassTeacherRepository extends JpaRepository<ClassTeacherEntity, Long>{

	List<ClassTeacherEntity> findByClassId(Long classId);

	
}
