package com.ijona.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijona.dto.ApiResponse;
import com.ijona.dto.StudentDTO;
import com.ijona.exceptions.StudentException;
import com.ijona.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<StudentDTO> addStudentHandler(@Valid @RequestBody StudentDTO studentDto)
			throws StudentException {
		StudentDTO dto = studentService.addNewStudent(studentDto);

		return new ResponseEntity<StudentDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDTO> updateStudentHandler(@Valid @RequestBody StudentDTO studentDTO,
			@PathVariable Integer id) throws StudentException {

		StudentDTO sdto = studentService.updateExistingStudent(studentDTO, id);

		return new ResponseEntity<StudentDTO>(sdto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentByIDHandler(@PathVariable Integer id) throws StudentException {
		StudentDTO sdto = studentService.getStudentByID(id);

		return new ResponseEntity<StudentDTO>(sdto, HttpStatus.FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudentsHandler() throws StudentException {
		List<StudentDTO> sdtos = studentService.getAllStudents();

		return new ResponseEntity<List<StudentDTO>>(sdtos, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudentByIDhandler(@PathVariable Integer id) throws StudentException{
		this.studentService.deleteStudentByID(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student deleted successfully..",true),HttpStatus.OK);
	}
	
	

}
