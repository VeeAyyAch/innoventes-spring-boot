package com.innoventes.test.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innoventes.test.app.dto.CompanyDTO;
import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.mapper.CompanyMapper;
import com.innoventes.test.app.service.CompanyService;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/companies")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<Company> companyList = companyService.getAllCompanies();
		
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
		
		for (Company entity : companyList) {
			companyDTOList.add(companyMapper.getCompanyDTO(entity));
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(companyDTOList);
	}

	//Task 3:
	@GetMapping("/companies/CompanyId")
	public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable("id") long id){
		CompanyDTO companyDTO=companyService.getComapnyById(id);
		return new ResponseEntity<>(companyDTO, HttpStatus.OK);
	}

	//Task 4:
	@GetMapping("/companies/CompanyCode")
	public ResponseEntity<CompanyDTO> findCompanyByCode(@PathVariable("code") String code){
		CompanyDTO companyDTO=companyService.getComapnyByCode(code);
		return new ResponseEntity<>(companyDTO, HttpStatus.OK);
	}

//	Task 7:
	@PatchMapping("/{companyId}")
	public ResponseEntity<CompanyDTO> partialUpdateCompany(@PathVariable("id") long companyId, @RequestBody CompanyDTO companyDTO){
		CompanyDTO updatedCompany =companyService.partialUpdateCompany(companyId,companyDTO);
		if(updatedCompany!=null){
			return ResponseEntity.ok(updatedCompany);
		}else{
			return ResponseEntity.notFound().build();
		}
	}


	@PostMapping("/companies")
	public ResponseEntity<CompanyDTO> addCompany(@Valid @RequestBody CompanyDTO companyDTO)
			throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company newCompany = companyService.addCompany(company);
		CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
				.toUri();
		return ResponseEntity.created(location).body(newCompanyDTO);
	}

	@PutMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable(value = "id") Long id,
			@Valid @RequestBody CompanyDTO companyDTO) throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company updatedCompany = companyService.updateCompany(id, company);
		CompanyDTO updatedCompanyDTO = companyMapper.getCompanyDTO(updatedCompany);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(updatedCompanyDTO);
	}

	@DeleteMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable(value = "id") Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}

	public String getMessage(String exceptionCode) {
		return messageSource.getMessage(exceptionCode, null, LocaleContextHolder.getLocale());
	}

}
