package com.energize.test.company.controller;

import com.energize.test.company.dto.EmployeeDTO;
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
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
       EmployeeDTO createdEmployee = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/{surname}")
    public ResponseEntity<EmployeeDTO> findEmployeeBySurname(@PathVariable("surname") String surname) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeBySurname(surname);
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }
}
