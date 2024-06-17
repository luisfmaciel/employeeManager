package br.edu.infnet.employeemanager.service;

import br.edu.infnet.employeemanager.model.Department;
import br.edu.infnet.employeemanager.model.Employee;
import br.edu.infnet.employeemanager.repository.DepartmentRepository;
import br.edu.infnet.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee, Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            employee.setDepartment(optionalDepartment.get());
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeUpdated) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeUpdated.setId(optionalEmployee.get().getId());
            employeeUpdated.setDepartment(optionalEmployee.get().getDepartment());
            return employeeRepository.save(employeeUpdated);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
