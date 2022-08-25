package com.energize.test.company.service;

import com.energize.test.company.domain.Employee;
import com.energize.test.company.dto.EmployeeDTO;
import com.energize.test.company.mapper.Mapper;
import com.energize.test.company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public boolean isEmployeeExist(String surname){
        boolean isExist = false;
        if (employeeRepository.findByEmployeeSurname(surname) != null)
            isExist = true;
        return isExist;

    }

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Mapper mapper = new Mapper();
        Employee employee = mapper.convertToEntity(employeeDTO, Employee.class);
        Employee createdEmployee = employeeRepository.save(employee);
        return mapper.convertToDto(createdEmployee, EmployeeDTO.class);

    }

    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeBySurname(String surname) {
        Mapper mapper = new Mapper();
        Employee employee = employeeRepository.findByEmployeeSurname(surname);
        return mapper.convertToDto(employee, EmployeeDTO.class);
    }
}
