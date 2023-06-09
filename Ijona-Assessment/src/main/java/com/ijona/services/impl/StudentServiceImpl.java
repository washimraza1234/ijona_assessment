package com.ijona.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijona.dto.StudentDTO;
import com.ijona.exceptions.StudentException;
import com.ijona.model.Student;
import com.ijona.repository.StudentRepo;
import com.ijona.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	// helps in mapping objects from one model to another, reducing the need for
	// manual mapping code.
	@Autowired
	private ModelMapper modelMapper;

	// Used to convert DTO variables to Entity variables;
	private Student studentDTOtoStudent(StudentDTO dto) {

		Student student = this.modelMapper.map(dto, Student.class);

		return student;
	}

	private StudentDTO studentToStudentDTO(Student student) {

		StudentDTO studentDto = this.modelMapper.map(student, StudentDTO.class);

		return studentDto;

	}

	@Override
	public StudentDTO addNewStudent(StudentDTO dto) throws StudentException {
		Student student = studentDTOtoStudent(dto);

		Student savedStudent = repo.save(student);

		if (savedStudent != null)
			return studentToStudentDTO(savedStudent);

		else
			throw new StudentException("Please enter Student details...");

	}

	@Override
	public StudentDTO updateExistingStudent(StudentDTO dto, Integer id) throws StudentException {
		Student student = this.repo.findById(id)
				.orElseThrow(() -> new StudentException("Student not found with ID: " + id));

		student.setName(dto.getName());
		student.setAge(dto.getAge());
		student.setGrade(dto.getGrade());

		Student updatedStudent = this.repo.save(student);

		return studentToStudentDTO(updatedStudent);
	}

	@Override
	public StudentDTO getStudentByID(Integer id) throws StudentException {

		Student student = this.repo.findById(id)
				.orElseThrow(() -> new StudentException("Student not found with ID:" + id));

		return studentToStudentDTO(student);
	}

	@Override
	public List<StudentDTO> getAllStudents() throws StudentException {

		List<Student> students = this.repo.findAll();

		if (students.size() > 0) {

			// Converting list of student to list StudentDto
			List<StudentDTO> studentDto = students.stream().map(student -> studentToStudentDTO(student))
					.collect(Collectors.toList());
			return studentDto;

		} else
			throw new StudentException("No Students found..");
	}

	@Override
	public void deleteStudentByID(Integer id) throws StudentException {

		Student student = this.repo.findById(id)
				.orElseThrow(() -> new StudentException("No student detalis found with ID: " + id));
		repo.delete(student);

	}

}
