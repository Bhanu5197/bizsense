package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.AddressDto;
import org.example.dto.EmployeeDto;
import org.example.mapper.AddressMapper;
import org.example.mapper.EmployeeMapper;
import org.example.repository.Address;
import org.example.repository.AddressRepository;
import org.example.repository.Employee;
import org.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeMapper.toEntity(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = employeeService.createEmployee(employeeDto);

        // Assert
        assertEquals(employeeDto, result);
        verify(employeeMapper, times(1)).toEntity(employeeDto);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeMapper, times(1)).toDto(employee);
    }

    @Test
    public void testCreateEmployeeAddress() {
        // Arrange
        Long employeeId = 1L;
        AddressDto addressDto = new AddressDto();
        Employee employee = new Employee();
        Address address = new Address();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(addressMapper.toEntity(addressDto)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toDto(address)).thenReturn(addressDto);

        // Act
        AddressDto result = employeeService.createEmployeeAddress(employeeId, addressDto);

        // Assert
        assertEquals(addressDto, result);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(addressMapper, times(1)).toEntity(addressDto);
        verify(addressRepository, times(1)).save(address);
        verify(addressMapper, times(1)).toDto(address);
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Long id = 1L;
        EmployeeDto employeeDto = new EmployeeDto();
        Employee existingEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);
        when(employeeMapper.toDto(existingEmployee)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = employeeService.updateEmployee(id, employeeDto);

        // Assert
        assertEquals(employeeDto, result);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toDto(existingEmployee);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    public void testUpdateEmployeeAddress() {
        // Arrange
        Long employeeId = 1L;
        Long addressId = 1L;
        AddressDto addressDto = new AddressDto();
        Address existingAddress = new Address();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.save(existingAddress)).thenReturn(existingAddress);
        when(addressMapper.toDto(existingAddress)).thenReturn(addressDto);

        // Act
        AddressDto result = employeeService.updateEmployeeAddress(employeeId, addressId, addressDto);

        // Assert
        assertEquals(addressDto, result);
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressMapper, times(1)).toDto(existingAddress);
        verify(addressRepository, times(1)).save(existingAddress);
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Long id = 1L;
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(employee)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = employeeService.getEmployeeById(id);

        // Assert
        assertEquals(employeeDto, result);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toDto(employee);
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(new EmployeeDto());
        employeeDtos.add(new EmployeeDto());
        when(employeeMapper.toDto(employees)).thenReturn(employeeDtos);

        // Act
        List<EmployeeDto> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(employeeDtos.size(), result.size());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).toDto(employees);
    }

    @Test
    public void testGetAllAddressesOfEmployee() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee();
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());
        addresses.add(new Address());
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(addressRepository.findAllByEmployee(employee)).thenReturn(Optional.of(addresses));
        List<AddressDto> addressDtos = new ArrayList<>();
        addressDtos.add(new AddressDto());
        addressDtos.add(new AddressDto());
        when(addressMapper.toDto(addresses)).thenReturn(addressDtos);

        // Act
        List<AddressDto> result = employeeService.getAllAddressesOfEmployee(employeeId);

        // Assert
        assertEquals(addressDtos.size(), result.size());
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(addressRepository, times(1)).findAllByEmployee(employee);
        verify(addressMapper, times(1)).toDto(addresses);
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        Long id = 1L;

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteEmployeeThrowsException() {
        // Arrange
        Long id = 1L;
        doThrow(EntityNotFoundException.class).when(employeeRepository).deleteById(id);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(id));
        verify(employeeRepository, times(1)).deleteById(id);
    }
}
