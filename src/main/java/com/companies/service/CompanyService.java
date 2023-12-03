package com.companies.service;

import com.companies.dto.CompanyDto;
import com.companies.model.Company;
import com.companies.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Add Company
    public CompanyDto addCompany(CompanyDto companyDto) {
        Company company = new Company(
                UUID.randomUUID(),
                companyDto.getName(),
                companyDto.getSize(),
                companyDto.getAddress()
        );

        Company savedCompany = companyRepository.save(company);

        return new CompanyDto(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getSize(),
                savedCompany.getAddress()
        );
    }


    // Delete Company by Id
    public void deleteCompanyById(UUID companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(String.valueOf(companyId));

        if (optionalCompany.isPresent()) {
            companyRepository.delete(optionalCompany.get());
        } else {
            throw new EntityNotFoundException("Company not found for id: " + companyId);
        }
    }


    // Modify Company by Id
    public CompanyDto updateCompany(UUID companyId, CompanyDto updatedCompanyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(String.valueOf(companyId));

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            existingCompany.setName(updatedCompanyDto.getName());
            existingCompany.setSize(updatedCompanyDto.getSize());
            existingCompany.setAddress(updatedCompanyDto.getAddress());

            Company savedCompany = companyRepository.save(existingCompany);

            return new CompanyDto(
                    savedCompany.getId(),
                    savedCompany.getName(),
                    savedCompany.getSize(),
                    savedCompany.getAddress()
            );

        } else {
            throw new EntityNotFoundException("Company not found for id: " + companyId);
        }
    }

    public CompanyDto getCompanyById(UUID companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(String.valueOf(companyId));

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            return new CompanyDto(
                    existingCompany.getId(),
                    existingCompany.getName(),
                    existingCompany.getSize(),
                    existingCompany.getAddress()
            );
        } else {
            throw new EntityNotFoundException("Company not found for id: " + companyId);
        }
    }
}