package org.example.mapper;

import org.example.dto.EmployeeDto;
import org.example.repository.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {

    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    public void testToEntity() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmail("john.doe@gmail.com");

        Employee employee = mapper.toEntity(employeeDto);

        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@gmail.com", employee.getEmail());
    }

    @Test
    public void testToDto() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@gmail.com");

        EmployeeDto employeeDto = mapper.toDto(employee);

        assertEquals(2L, employeeDto.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@gmail.com", employee.getEmail());
    }

    @Test
    public void testToDtoList() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@gmail.com");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmail("jane.smith@gmail.com");

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        List<EmployeeDto> employeeDtoList = mapper.toDto(employeeList);

        assertEquals(2, employeeDtoList.size());
        assertEquals(1L, employeeDtoList.get(0).getId());
        assertEquals("John", employeeDtoList.get(0).getFirstName());
        assertEquals("Doe", employeeDtoList.get(0).getLastName());
        assertEquals("john.doe@gmail.com", employeeDtoList.get(0).getEmail());
        assertEquals(2L, employeeDtoList.get(1).getId());
        assertEquals("Jane", employeeDtoList.get(1).getFirstName());
        assertEquals("Smith", employeeDtoList.get(1).getLastName());
        assertEquals("jane.smith@gmail.com", employeeDtoList.get(1).getEmail());
    }
}
