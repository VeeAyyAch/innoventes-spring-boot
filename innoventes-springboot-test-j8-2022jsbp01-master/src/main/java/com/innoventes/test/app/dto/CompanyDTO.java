package com.innoventes.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyDTO {

	private Long id;
	//Task 1
	@NotBlank(message = "Company name is mandatory and can not be empty")
	private String companyName;
	@NotBlank(message = "Email is mandatory and can not be empty")
	@Email(message ="invalid email format.")
	private String email;

	@PositiveOrZero(message = "Strength should be a positive number or Zero")
	//Task 6
	@EvenNumberOrZero(message = "Strength should be an even number or zero.")
	private Integer strength;
	
	private String webSiteURL;

	@Size(max = 5,message = "Company code length should be 5 characters.")
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[eEnN]?$", message = "invalid company code format.")
	private String companyCode;
}
