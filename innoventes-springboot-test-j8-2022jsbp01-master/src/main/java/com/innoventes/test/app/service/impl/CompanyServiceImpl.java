package com.innoventes.test.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.innoventes.test.app.dto.CompanyDTO;
//import jdk.vm.ci.meta.Constant;
//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.error.ApplicationErrorCodes;
import com.innoventes.test.app.exception.ResourceNotFoundException;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.repository.CompanyRepository;
import com.innoventes.test.app.service.CompanyService;
import com.innoventes.test.app.util.ServiceHelper;

import javax.validation.ConstraintViolation;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ServiceHelper serviceHelper;

	@Override
	public List<Company> getAllCompanies() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyRepository.findAll().forEach(companyList::add);
		return companyList;
	}

	@Override
	public Company addCompany(Company company) throws ValidationException {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) throws ValidationException {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		company.setId(existingCompanyRecord.getId());
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		companyRepository.deleteById(existingCompanyRecord.getId());
	}

	//Task 3
	@Override
	public CompanyDTO getComapnyById(long id) {
		Company company = companyRepository.findById(id).get();
		CompanyDTO companyDTO=new CompanyDTO();
		companyDTO.setId(company.getId());
		companyDTO.setCompanyName(company.getCompanyName());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setStrength(company.getStrength());
		companyDTO.setWebSiteURL(company.getWebSiteURL());
		return companyDTO;
	}

	//Task 4
	@Override
	public CompanyDTO getComapnyByCode(String code) {
		Company company = companyRepository.findByCompanyCode(code);
		CompanyDTO companyDTO=new CompanyDTO();
		companyDTO.setId(company.getId());
		companyDTO.setCompanyName(company.getCompanyName());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setStrength(company.getStrength());
		companyDTO.setWebSiteURL(company.getWebSiteURL());
		return companyDTO;
	}

	//Task 7:
	@Override
	public CompanyDTO partialUpdateCompany(long companyId, CompanyDTO companyDTO) {
		Company existingCompany=companyRepository.findById(companyId).orElse(null);

		if(existingCompany==null) {
			return null;
		}
//		BeanUtils.copyProperties(companyDTO,existingCompany),getNullPropertyNames(companyDTO);
//
//		Set<ConstraintViolation<Company>>violations=validator.validate(existingCompany);
//		if(!violations.isEmpty()){
//
//		}
		return null;
	}


}
