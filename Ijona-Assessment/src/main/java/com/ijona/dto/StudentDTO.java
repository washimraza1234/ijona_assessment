package com.ijona.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "Student must be of 4 character !!")
	private String name;

	private Integer age;

	private String grade;

}
