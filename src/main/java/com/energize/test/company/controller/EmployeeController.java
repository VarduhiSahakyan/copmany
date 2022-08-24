package com.energize.test.company.controller;

import com.energize.test.company.domain.Employee;
import com.energize.test.company.dto.EmployeeDTO;
import com.energize.test.company.mapper.Mapper;
import com.energize.test.company.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Mapper mapper = new Mapper();
        Employee createdEmployee = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok(mapper.convertToDto(createdEmployee, EmployeeDTO.class));
    }

    @GetMapping("/{surname}")
    public ResponseEntity<EmployeeDTO> getEmployeeBySurname(@PathVariable("surname") String surname){
        EmployeeDTO employeeDTO = employeeService.findEmployeeBySurname(surname);
        return ResponseEntity.ok(employeeDTO);
    }
}
