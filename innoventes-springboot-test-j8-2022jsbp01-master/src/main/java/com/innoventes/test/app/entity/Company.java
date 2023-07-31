package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "company_seq", allocationSize = 1, name = "company_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	private Long id;

	//Task 1
	@NotBlank(message = "company name is mandatory and cannot be empty")
	@Column(name = "company_name")
	private String companyName;

	@NotBlank(message = "email is mandatory and cannot be empty")
	@Column(name = "email")
	private String email;

	@PositiveOrZero(message = "Strength should be a positive and number or zero.")
	@Column(name = "strength")
	private Integer strength;
	
	@Column(name = "website_url")
	private String webSiteURL;

	//Task 2
	@Column(name="company_code",unique = true)
	@Size(max = 5,message = "Company code length should be 5 characters.")
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[eEnN]?$", message = "invalid company code format.")
	private String companyCode;
}
