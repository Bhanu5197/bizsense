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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public AddressDto createEmployeeAddress(Long employeeId, AddressDto addressDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        Address address = addressMapper.toEntity(addressDTO);
        address.setEmployee(employee);

        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        updateEmployeeData(existingEmployee, employeeDTO);

        return employeeMapper.toDto(employeeRepository.save(existingEmployee));
    }

    @Override
    public AddressDto updateEmployeeAddress(Long employeeId, Long addressId, AddressDto addressDTO) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));

        updateAddressData(existingAddress, addressDTO);

        return addressMapper.toDto(addressRepository.save(existingAddress));
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public List<AddressDto> getAllAddressesOfEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        List<Address> addresses = addressRepository.findAllByEmployee(employee)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with Employee id: " + employeeId));
        return addressMapper.toDto(addresses);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Utility method to update existing Employee entity with data from EmployeeDTO
    private void updateEmployeeData(Employee employee, EmployeeDto employeeDTO) {
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
    }

    // Utility method to update existing Address entity with data from AddressDTO
    private void updateAddressData(Address address, AddressDto addressDTO) {
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
    }
}
