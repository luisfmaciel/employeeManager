package br.edu.infnet.employeemanager.service;

import br.edu.infnet.employeemanager.model.Department;
import br.edu.infnet.employeemanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            updatedDepartment.setId(optionalDepartment.get().getId());
            updatedDepartment.setEmployees(optionalDepartment.get().getEmployees());
            return departmentRepository.save(updatedDepartment);
        } else {
            throw new RuntimeException("Department not found with id " + id);
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
