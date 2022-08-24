package com.energize.test.company.service;

import com.energize.test.company.domain.Employee;
import com.energize.test.company.dto.EmployeDTO;
import com.energize.test.company.mapper.Mapper;
import com.energize.test.company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final Mapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, Mapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public void saveEmploye(EmployeDTO employeDTO){
        Employee employee = employeeMapper.convertToEntity(employeDTO, Employee.class);
        employeeRepository.save(employee);

    }


    @Transactional(readOnly = true)
    public EmployeDTO findEmployeeBySurname(String surname){
        Employee employee = employeeRepository.findEmployeeByEmploeeSurname(surname);
        return employeeMapper.convertToDto(employee, EmployeDTO.class);
    }
}
