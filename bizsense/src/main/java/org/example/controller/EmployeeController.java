package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.AddressDto;
import org.example.dto.EmployeeDto;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employee")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create new Employee
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    // Create Employee Address
    @PostMapping("/{employeeId}/address")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto createEmployeeAddress(@PathVariable Long employeeId, @RequestBody @Valid AddressDto addressDto) {
        return employeeService.createEmployeeAddress(employeeId, addressDto);
    }

    // Update Employee
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    // Update Employee Address
    @PutMapping("/{employeeId}/address/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDto updateEmployeeAddress(@PathVariable Long employeeId, @PathVariable Long addressId, @RequestBody @Valid AddressDto addressDto) {
        return employeeService.updateEmployeeAddress(employeeId, addressId, addressDto);
    }

    // Fetch Employee by Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Fetch All Employees
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Fetch all addresses of a particular employee
    @GetMapping("/{employeeId}/address")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDto> getAllAddressesOfEmployee(@PathVariable Long employeeId) {
        return employeeService.getAllAddressesOfEmployee(employeeId);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
