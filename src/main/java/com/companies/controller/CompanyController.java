package com.companies.controller;

import com.companies.dto.CompanyDto;
import com.companies.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto addedCompany = companyService.addCompany(companyDto);
        return new ResponseEntity<>(addedCompany, HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID companyId) {
        companyService.deleteCompanyById(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable UUID companyId, @RequestBody CompanyDto updatedCompanyDto) {
        CompanyDto updatedCompany = companyService.updateCompany(companyId, updatedCompanyDto);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable UUID companyId) {
        CompanyDto companyDto = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

}