package com.energize.test.company.repository;

import com.energize.test.company.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   Employee findEmployeeByEmploeeSurname(String surname);

}
