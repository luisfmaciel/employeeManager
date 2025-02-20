package br.edu.infnet.employeemanager.repository;

import br.edu.infnet.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}