package com.energize.test.company.service;

import com.energize.test.company.advisor.AlreadyExistsException;
import com.energize.test.company.advisor.NotValidException;
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

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        isValid(employeeDTO);
        isExist(employeeDTO);
        Mapper mapper = new Mapper();
        Employee employee = mapper.convertToEntity(employeeDTO, Employee.class);
        Employee createdEmployee = employeeRepository.save(employee);
        return mapper.convertToDto(createdEmployee, EmployeeDTO.class);

    }

    public void isValid(EmployeeDTO employeeDTO) {
        if ((employeeDTO.getEmployeeName() == null || employeeDTO.getEmployeeName().isEmpty()) ||
                (employeeDTO.getEmployeeSurname() == null || employeeDTO.getEmployeeSurname().isEmpty())) {
            throw new NotValidException("Employee name or surname is not valid");
        }
    }

    public void isExist(EmployeeDTO employeeDTO) {
        if (isEmployeeExist(employeeDTO.getEmployeeSurname())) {
            throw new AlreadyExistsException("Employee already exist");
        }
    }

    public boolean isEmployeeExist(String surname) {
        boolean isExist = false;
        if (employeeRepository.findByEmployeeSurname(surname) != null)
            isExist = true;
        return isExist;

    }


    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeBySurname(String surname) {
        Mapper mapper = new Mapper();
        Employee employee = employeeRepository.findByEmployeeSurname(surname);
        return mapper.convertToDto(employee, EmployeeDTO.class);
    }
}
