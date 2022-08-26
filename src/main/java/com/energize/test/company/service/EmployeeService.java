package com.energize.test.company.service;

import com.energize.test.company.advisor.AlreadyExistsException;
import com.energize.test.company.advisor.NotFoundException;
import com.energize.test.company.advisor.NotValidException;
import com.energize.test.company.domain.Employee;
import com.energize.test.company.dto.EmployeeDTO;
import com.energize.test.company.mapper.Mapper;
import com.energize.test.company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        isEmployeeValid(employeeDTO);
        isEmployeeExist(employeeDTO);
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

    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        isEmployeeExist(employeeDTO);
        Mapper mapper = new Mapper();
        Employee employee = employeeRepository.findEmployeeById(employeeDTO.getId());
        if (employee == null)
            throw new NotFoundException("Employee not exist");
        employeeRepository.save(mapper.convertToEntity(employeeDTO, Employee.class));
        return employeeDTO;
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null)
            throw new NotFoundException("Employee not exist");
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployee() {
        Mapper mapper = new Mapper();
        List<Employee> employees = employeeRepository.findAll();
        return mapper.mapList(employees, EmployeeDTO.class);


    }


    public void isEmployeeExist(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmployeeSurname(employeeDTO.getEmployeeSurname()) != null) {
            throw new AlreadyExistsException("Employee already exist");
        }
    }

    public void isEmployeeValid(EmployeeDTO employeeDTO) {
        if ((employeeDTO.getEmployeeName() == null || employeeDTO.getEmployeeName().isEmpty()) ||
                (employeeDTO.getEmployeeSurname() == null || employeeDTO.getEmployeeSurname().isEmpty())) {
            throw new NotValidException("Employee name or surname is not valid");
        }
    }

}
