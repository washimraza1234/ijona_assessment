package com.ijona.services;

import java.util.List;

import com.ijona.dto.StudentDTO;
import com.ijona.exceptions.StudentException;

public interface StudentService {

	// We are not supposed to expose the entity class data or variables to the users;
	// that is the reason we are using Data Transfer Object(DTO);
	
	public StudentDTO addNewStudent(StudentDTO dto) throws StudentException;
	
	public StudentDTO updateExistingStudent(StudentDTO dto, Integer id) throws StudentException;
	
	public StudentDTO getStudentByID(Integer id) throws StudentException;
	
	public List<StudentDTO> getAllStudents() throws StudentException;
	
	public void deleteStudentByID(Integer id) throws StudentException;

}
