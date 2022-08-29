package com.energize.test.company.service;

import com.energize.test.company.zexceptions.AlreadyExistsException;
import com.energize.test.company.zexceptions.EmployeeNotFoundException;
import com.energize.test.company.zexceptions.NotValidException;
import com.energize.test.company.domain.Employee;
import com.energize.test.company.dto.EmployeeDTO;
import com.energize.test.company.mapper.Mapper;
import com.energize.test.company.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        logger.info("Employee add method works!");
        isEmployeeValid(employeeDTO);
        isEmployeeExist(employeeDTO);
        Mapper mapper = new Mapper();
        Employee employee = mapper.convertToEntity(employeeDTO, Employee.class);
        Employee createdEmployee = employeeRepository.save(employee);
        return mapper.convertToDto(createdEmployee, EmployeeDTO.class);

    }

    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeBySurname(String surname) {
        logger.info("Employee get method works!");
        Mapper mapper = new Mapper();
        Employee employee = employeeRepository.findByEmployeeSurname(surname);
        if (employee == null) {
            logger.error("Employee not exist whit last name!");
            logger.debug("Employee not exist whit last name: {}", surname );
            throw new EmployeeNotFoundException("Employee not exist");
        }
        return mapper.convertToDto(employee, EmployeeDTO.class);
    }

    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        logger.info("Employee update method works!");
        isEmployeeExist(employeeDTO);
        Mapper mapper = new Mapper();
        Employee employee = employeeRepository.findEmployeeById(employeeDTO.getId());
        if (employee == null) {
            logger.error("Employee not exist whit last name!");
            logger.debug("Employee not exist whit last name: {}", employeeDTO.getEmployeeSurname());
            throw new EmployeeNotFoundException("Employee not exist");
        }
        employeeRepository.save(mapper.convertToEntity(employeeDTO, Employee.class));
        return employeeDTO;
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        logger.info("Employee delete method works!");
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            logger.error("Employee not exist whit id!" );
            logger.debug("Employee not exist whit id: {}", id );
            throw new EmployeeNotFoundException("Employee not exist");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployee() {
        logger.info("Employee gatAll method works!");
        Mapper mapper = new Mapper();
        List<Employee> employees = employeeRepository.findAll();
        return mapper.mapList(employees, EmployeeDTO.class);

    }

    public void isEmployeeExist(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmployeeSurname(employeeDTO.getEmployeeSurname()) != null) {
            logger.error("This employee already exists!");
            logger.debug("This employee already exists: {}",employeeDTO.getEmployeeSurname());
            throw new AlreadyExistsException("Employee already exist");
        }
    }

    public void isEmployeeValid(EmployeeDTO employeeDTO) {
        if ((employeeDTO.getEmployeeName() == null || employeeDTO.getEmployeeName().isEmpty()) ||
                (employeeDTO.getEmployeeSurname() == null || employeeDTO.getEmployeeSurname().isEmpty())) {
            logger.error("This employee name or surname not valid!");
            logger.debug("This employee name or surname not valid: {}", employeeDTO.getEmployeeSurname());
            throw new NotValidException("Employee name or surname is not valid");
        }
    }

}
