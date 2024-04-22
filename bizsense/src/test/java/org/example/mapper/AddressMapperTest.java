package org.example.mapper;

import org.example.dto.AddressDto;
import org.example.repository.Address;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperTest {

    private final AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    @Test
    public void testToEntity() {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("123 Main St");
        addressDto.setCity("Anytown");
        addressDto.setZipCode("12345");

        Address address = mapper.toEntity(addressDto);

        assertEquals("123 Main St", address.getStreet());
        assertEquals("Anytown", address.getCity());
        assertEquals("12345", address.getZipCode());
    }

    @Test
    public void testToDto() {
        Address address = new Address();
        address.setStreet("456 Elm St");
        address.setCity("Othertown");
        address.setZipCode("54321");

        AddressDto addressDto = mapper.toDto(address);

        assertEquals("456 Elm St", addressDto.getStreet());
        assertEquals("Othertown", addressDto.getCity());
        assertEquals("54321", addressDto.getZipCode());
    }

    @Test
    public void testToDtoList() {
        Address address1 = new Address();
        address1.setStreet("123 Main St");
        address1.setCity("Anytown");
        address1.setZipCode("12345");

        Address address2 = new Address();
        address2.setStreet("456 Elm St");
        address2.setCity("Othertown");
        address2.setZipCode("54321");

        List<Address> addressList = Arrays.asList(address1, address2);

        List<AddressDto> addressDtoList = mapper.toDto(addressList);

        assertEquals(2, addressDtoList.size());
        assertEquals("123 Main St", addressDtoList.get(0).getStreet());
        assertEquals("Anytown", addressDtoList.get(0).getCity());
        assertEquals("12345", addressDtoList.get(0).getZipCode());
        assertEquals("456 Elm St", addressDtoList.get(1).getStreet());
        assertEquals("Othertown", addressDtoList.get(1).getCity());
        assertEquals("54321", addressDtoList.get(1).getZipCode());
    }
}
