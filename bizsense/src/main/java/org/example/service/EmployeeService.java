package org.example.service;

import org.example.dto.AddressDto;
import org.example.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    AddressDto createEmployeeAddress(Long employeeId, AddressDto addressDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    AddressDto updateEmployeeAddress(Long employeeId, Long addressId, AddressDto addressDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    List<AddressDto> getAllAddressesOfEmployee(Long employeeId);

    void deleteEmployee(Long id);
}
