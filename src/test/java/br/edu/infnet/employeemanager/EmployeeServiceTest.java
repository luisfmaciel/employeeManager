package br.edu.infnet.employeemanager;

import br.edu.infnet.employeemanager.model.Department;
import br.edu.infnet.employeemanager.model.Employee;
import br.edu.infnet.employeemanager.repository.DepartmentRepository;
import br.edu.infnet.employeemanager.repository.EmployeeRepository;
import br.edu.infnet.employeemanager.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setName("Fulano da Silva");

        Employee employee2 = new Employee();
        employee2.setName("Sicrano da Silva");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Fulano da Silva", employees.get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setName("Fulano da Silva");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertTrue(foundEmployee.isPresent());
        assertEquals("Fulano da Silva", foundEmployee.get().getName());
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Fulano da Silva");

        Department department = new Department();
        department.setName("IT");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee, 1L);
        assertNotNull(createdEmployee);
        assertEquals("Fulano da Silva", createdEmployee.getName());
        assertEquals("IT", createdEmployee.getDepartment().getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setName("Fulano da Silva");

        Department department = new Department();
        department.setName("IT");
        existingEmployee.setDepartment(department);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Fulano da Silva");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);
        assertNotNull(result);
        assertEquals("Fulano da Silva", result.getName());
        assertEquals("IT", result.getDepartment().getName());
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}

