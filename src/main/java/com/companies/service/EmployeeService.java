package com.companies.service;

import com.companies.dto.EmployeeDto;
import com.companies.model.Employee;
import com.companies.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                UUID.randomUUID(),
                employeeDto.getName(),
                employeeDto.getSurname(),
                employeeDto.getEmail(),
                employeeDto.getPhoneNumber(),
                employeeDto.getSalary(),
                employeeDto.getPosition(),
                employeeDto.getCompanyId()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getSurname(),
                savedEmployee.getEmail(),
                savedEmployee.getPhoneNumber(),
                savedEmployee.getSalary(),
                savedEmployee.getPosition(),
                savedEmployee.getCompanyId()
        );
    }

    public void deleteEmployeeById(UUID employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(String.valueOf(employeeId));

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            employeeRepository.delete(existingEmployee);
        } else {
            throw new EntityNotFoundException("Employee not found for id: " + employeeId);
        }
    }

    public EmployeeDto updateEmployee(UUID employeeId, EmployeeDto updatedEmployeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(String.valueOf(employeeId));

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setName(updatedEmployeeDto.getName());
            existingEmployee.setSurname(updatedEmployeeDto.getSurname());
            existingEmployee.setEmail(updatedEmployeeDto.getEmail());
            existingEmployee.setPhoneNumber(updatedEmployeeDto.getPhoneNumber());
            existingEmployee.setSalary(updatedEmployeeDto.getSalary());
            existingEmployee.setPosition(updatedEmployeeDto.getPosition());
            existingEmployee.setCompanyId(updatedEmployeeDto.getCompanyId());

            Employee savedEmployee = employeeRepository.save(existingEmployee);

            return new EmployeeDto(
                    savedEmployee.getId(),
                    savedEmployee.getName(),
                    savedEmployee.getSurname(),
                    savedEmployee.getEmail(),
                    savedEmployee.getPhoneNumber(),
                    savedEmployee.getSalary(),
                    savedEmployee.getPosition(),
                    savedEmployee.getCompanyId()
            );
        } else {
            throw new EntityNotFoundException("Employee not found for id: " + employeeId);
        }
    }

    public EmployeeDto getEmployeeById(UUID employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(String.valueOf(employeeId));

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            return new EmployeeDto(
                    existingEmployee.getId(),
                    existingEmployee.getName(),
                    existingEmployee.getSurname(),
                    existingEmployee.getEmail(),
                    existingEmployee.getPhoneNumber(),
                    existingEmployee.getSalary(),
                    existingEmployee.getPosition(),
                    existingEmployee.getCompanyId()
            );
        } else {
            throw new EntityNotFoundException("Employee not found for id: " + employeeId);
        }
    }
}
