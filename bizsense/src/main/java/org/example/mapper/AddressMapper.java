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
public interface AddressMapper {

    Address toEntity(AddressDto dto);
    AddressDto toDto(Address employee);
    List<AddressDto> toDto(List<Address> employee);
}
