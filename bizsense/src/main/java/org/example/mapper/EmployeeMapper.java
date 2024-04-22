package org.example.mapper;

import org.example.dto.AddressDto;
import org.example.dto.EmployeeDto;
import org.example.repository.Address;
import org.example.repository.Employee;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeDto dto);
    EmployeeDto toDto(Employee employee);
    List<EmployeeDto> toDto(List<Employee> employee);
}
