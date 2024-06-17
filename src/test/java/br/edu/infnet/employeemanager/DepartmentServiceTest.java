package br.edu.infnet.employeemanager;

import br.edu.infnet.employeemanager.model.Department;
import br.edu.infnet.employeemanager.repository.DepartmentRepository;
import br.edu.infnet.employeemanager.service.DepartmentService;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartments() {
        Department department1 = new Department();
        department1.setName("HR");

        Department department2 = new Department();
        department2.setName("IT");

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));

        List<Department> departments = departmentService.getAllDepartments();
        assertNotNull(departments);
        assertEquals(2, departments.size());
        assertEquals("HR", departments.get(0).getName());
    }

    @Test
    void testGetDepartmentById() {
        Department department = new Department();
        department.setName("HR");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = departmentService.getDepartmentById(1L);
        assertTrue(foundDepartment.isPresent());
        assertEquals("HR", foundDepartment.get().getName());
    }

    @Test
    void testCreateDepartment() {
        Department department = new Department();
        department.setName("HR");

        when(departmentRepository.save(department)).thenReturn(department);

        Department createdDepartment = departmentService.createDepartment(department);
        assertNotNull(createdDepartment);
        assertEquals("HR", createdDepartment.getName());
    }

    @Test
    void testUpdateDepartment() {
        Department existingDepartment = new Department();
        existingDepartment.setId(1L);
        existingDepartment.setName("HR");

        Department updatedDepartment = new Department();
        updatedDepartment.setName("IT");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);

        Department result = departmentService.updateDepartment(1L, updatedDepartment);
        assertNotNull(result);
        assertEquals("IT", result.getName());
        assertEquals(existingDepartment.getEmployees(), result.getEmployees());
    }

    @Test
    void testUpdateDepartment_NotFound() {
        Department updatedDepartment = new Department();
        updatedDepartment.setName("IT");

        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            departmentService.updateDepartment(1L, updatedDepartment);
        });

        assertEquals("Department not found with id 1", exception.getMessage());
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentRepository).deleteById(1L);

        departmentService.deleteDepartment(1L);

        verify(departmentRepository, times(1)).deleteById(1L);
    }
}