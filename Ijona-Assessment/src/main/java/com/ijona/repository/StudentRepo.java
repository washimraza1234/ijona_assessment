package com.ijona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijona.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

}
